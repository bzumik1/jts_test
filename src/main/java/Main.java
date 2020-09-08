import org.locationtech.jts.JTSVersion;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.locationtech.jts.geom.util.AffineTransformationBuilder;

import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var A = new Coordinate(0,0);
        var B = new Coordinate(10,0);
        var C = new Coordinate(10,1);
        var D = new Coordinate(0,1);
        var E = new Coordinate(-5,5);
        var END = (Coordinate)A.clone();


        var coordinates = new Coordinate[]{A, B, C, D, END}; //must include first and last point twice, not same point!!!!
        var geometryFactory = new GeometryFactory();
        var linearRing = geometryFactory.createLinearRing(coordinates);


        // Simply pass an array of Coordinate or a CoordinateSequence to its method
        var polygon = geometryFactory.createPolygon(linearRing); //can be created from coordinates too

        System.out.println("POLYGON");
        System.out.println(polygon);
        System.out.println("Is rectangle :"+ polygon.isRectangle());
        System.out.println("Polygon length is: "+polygon.getLength());
        System.out.println("Polygon area is: "+polygon.getArea());
        System.out.println("Polygon center is: "+polygon.getCentroid());



        //Scale transformation by hand
//        var centroid = polygonFromCoordinates.getCentroid();
//        AffineTransformation trans = new AffineTransformation();
//        trans.translate(-centroid.getX(), -centroid.getY());
//        polygonFromCoordinates.apply(trans);
//
//        trans = new AffineTransformation();
//        trans.scale(2, 2);
//        polygonFromCoordinates.apply(trans);
//
//        trans = new AffineTransformation();
//        trans.translate(centroid.getX(), centroid.getY());
//        polygonFromCoordinates.apply(trans);


        //Scale function
        var A2 = new Coordinate(0,0);
        var B2 = new Coordinate(10,0);
        var C2 = new Coordinate(10,1);
        var D2 = new Coordinate(0,1);
        var E2 = new Coordinate(-5,5);
        var END2 = (Coordinate)A2.clone();


        var coordinates2 = new Coordinate[]{A2, B2, C2, D2, END2}; //must include first and last point twice, not same point!!!!
        var scaledLinearRing = geometryFactory.createLinearRing(coordinates2);


        // Simply pass an array of Coordinate or a CoordinateSequence to its method
        var scaledPolygon = geometryFactory.createPolygon(scaledLinearRing);

        double scale = 2; //100%

        var centroid = scaledPolygon.getCentroid();


        scaledPolygon.apply(AffineTransformation.scaleInstance(2,2,centroid.getX(),centroid.getY()));
        System.out.println("\n\nSCALED POLYGON");
        System.out.println(scaledPolygon);
        System.out.println("Is rectangle :"+ scaledPolygon.isRectangle());
        System.out.println("Scaled polygon length is: "+scaledPolygon.getLength());
        System.out.println("Scaled polygon area is: "+scaledPolygon.getArea());
        System.out.println("Scaled polygon center is: "+scaledPolygon.getCentroid());

        //Intersection
        var A1 = new Coordinate(4,-5);
        var B1 = new Coordinate(5,-5);
        var C1 = new Coordinate(5,5);
        var D1 = new Coordinate(4,5);
        var E1 = new Coordinate(0,6);
        var END1 = (Coordinate)A1.clone();

        var coordinates1 = new Coordinate[]{A1, B1, C1, D1, END1}; //must include first and last point twice, not same point!!!!
        var intersectedLinearRing = geometryFactory.createLinearRing(coordinates1);
        var intersectedPolygon = geometryFactory.createPolygon(intersectedLinearRing); //can be created from coordinates too

        System.out.println("\n\nINTERSECTED LINEAR RING");
        System.out.println(intersectedLinearRing);
        System.out.println("Is rectangle :"+ intersectedLinearRing.isRectangle());
        System.out.println("Linear ring length is: "+intersectedLinearRing.getLength());
        System.out.println("Linear ring area is: "+intersectedLinearRing.getArea());
        System.out.println("Linear ring center is: "+intersectedLinearRing.getCentroid());

        System.out.println("\n\nINTERSECTIONS");
        var intersections = linearRing.intersection(intersectedLinearRing);
        Arrays.stream(intersections.getCoordinates()).forEach(coordinate -> System.out.println("Intersection: "+coordinate));


        //No intersection
        var A3 = new Coordinate(-100,-100);
        var B3 = new Coordinate(100,-100);
        var C3 = new Coordinate(100,100);
        var D3 = new Coordinate(-100,100);
        var E3 = new Coordinate(-200,200);
        var END3 = (Coordinate)A3.clone();

        var coordinates3 = new Coordinate[]{A3, B3, C3, D3, END3}; //must include first and last point twice, not same point!!!!
        var nonIntersectedLinearRing = geometryFactory.createLinearRing(coordinates3);
        var nonIntersectedPolygon = geometryFactory.createPolygon(nonIntersectedLinearRing); //can be created from coordinates too

        System.out.println("\n\nNON INTERSECTED LINEAR RING");
        System.out.println(nonIntersectedLinearRing);
        System.out.println("Is rectangle :"+ nonIntersectedLinearRing.isRectangle());
        System.out.println("Linear ring length is: "+nonIntersectedLinearRing.getLength());
        System.out.println("Linear ring area is: "+nonIntersectedLinearRing.getArea());
        System.out.println("Linear ring center is: "+nonIntersectedLinearRing.getCentroid());

        System.out.println("\n\nNO INTERSECTIONS");
        var noIntersections = linearRing.intersection(nonIntersectedLinearRing);
        Arrays.stream(noIntersections.getCoordinates()).forEach(coordinate -> System.out.println("Intersection: "+coordinate));

        //just curve
        var coordinates4 = coordinates1.clone();
        var lineString = geometryFactory.createLineString(coordinates4);

        System.out.println("\n\nLINE STRING");
        System.out.println(nonIntersectedLinearRing);
        System.out.println("Is rectangle :"+ nonIntersectedLinearRing.isRectangle());
        System.out.println("Linear ring length is: "+nonIntersectedLinearRing.getLength());
        System.out.println("Linear ring area is: "+nonIntersectedLinearRing.getArea());
        System.out.println("Linear ring center is: "+nonIntersectedLinearRing.getCentroid());
        lineString.apply(AffineTransformation.scaleInstance(2,2,lineString.getCentroid().getX(), centroid.getY()));

        //Buffer
        var stringCoordinates = "1316 114, 1306 115, 1295 116, 1283 120, 1273 124, 1262 130, 1253 137, 1244 142, 1234 149, 1226 155, 1219 163, 1212 172, 1204 183, 1199 199, 1197 210, 1193 223, 1190 235, 1188 245, 1186 258, 1185 269, 1184 281, 1183 299, 1184 313, 1184 325, 1186 339, 1188 351, 1191 362, 1194 374, 1197 387, 1202 398, 1209 408, 1218 417, 1229 423, 1239 429, 1252 437, 1264 443, 1273 448, 1285 454, 1297 458, 1309 462, 1320 464, 1333 465, 1344 466, 1356 466, 1366 462, 1371 450, 1376 440, 1382 430, 1387 418, 1391 406, 1394 394, 1397 382, 1399 371, 1404 362, 1410 353, 1418 344, 1427 335, 1437 327, 1446 321, 1457 316, 1468 310, 1480 305, 1495 299, 1505 297, 1517 295, 1527 294, 1542 293, 1553 292, 1567 292, 1577 292, 1595 292, 1611 292, 1625 295, 1636 295, 1649 298, 1663 303, 1674 307, 1686 310, 1696 312, 1709 317, 1722 322, 1733 327, 1744 337, 1754 346, 1767 358, 1776 369, 1782 378, 1787 388, 1791 398, 1794 409, 1795 424, 1795 436, 1793 446, 1788 455, 1778 464, 1768 466, 1758 463, 1750 455, 1743 445, 1735 433, 1731 419, 1729 407, 1729 394, 1729 384, 1731 371, 1735 360, 1740 350, 1745 340, 1751 328, 1756 317, 1759 306, 1762 295, 1765 284, 1768 271, 1770 259, 1772 249, 1772 239, 1772 229, 1772 219, 1771 209, 1770 199, 1767 189, 1763 177, 1758 165, 1751 150, 1744 134, 1739 125, 1733 116, 1725 108, 1714 105, 1699 103, 1689 102, 1675 101, 1662 100, 1652 99, 1640 97, 1628 96, 1617 95, 1606 95, 1594 94, 1581 92, 1568 92, 1556 92, 1542 92, 1531 92, 1519 92, 1506 93, 1494 93, 1478 93, 1466 93, 1454 94, 1440 95, 1429 95, 1418 96, 1408 97, 1398 98, 1387 98, 1377 99, 1366 100, 1356 101, 1346 103, 1336 106, 1326 107, 1321 112";
        var stringCoordinatesPairs = stringCoordinates.split(", ");
        var coordinates5 = Arrays.stream(stringCoordinatesPairs).map(s -> new Coordinate(Double.parseDouble(s.split(" ")[0]), Double.parseDouble(s.split(" ")[1]))).toArray(Coordinate[]::new);
        var curve = geometryFactory.createLineString(coordinates5);
        //var polygonForBuffer = geometryFactory.createPolygon(linearRingForBuffer);

        var minX = Arrays.stream(curve.getCoordinates()).min(Comparator.comparing(Coordinate::getX)).get();
        var maxX = Arrays.stream(curve.getCoordinates()).max(Comparator.comparing(Coordinate::getX)).get();
        var minY = Arrays.stream(curve.getCoordinates()).min(Comparator.comparing(Coordinate::getY)).get();
        var maxY = Arrays.stream(curve.getCoordinates()).max(Comparator.comparing(Coordinate::getY)).get();

        double maxWidth = maxX.getX()-minX.getX();
        double maxHeight = maxY.getY()-minY.getY();

        double xError = 100;
        double yError = 50;

        double absoluteScaleX = xError;
        double absoluteScaleY = yError;

        curve.apply(AffineTransformation.scaleInstance(1/absoluteScaleX,1/absoluteScaleY));

        var curveEnvelope = curve.buffer(1);

        curveEnvelope.apply(AffineTransformation.scaleInstance(absoluteScaleX,absoluteScaleY));
        curve.apply(AffineTransformation.scaleInstance(absoluteScaleX,absoluteScaleY));


        var stringCoordinatesForInvalidCurve = "1350 100, 1350 110, 1342 116, 1331 118, 1320 121, 1309 122, 1298 122, 1288 121, 1278 122, 1268 123, 1257 127, 1243 130, 1234 136, 1228 145, 1226 156, 1228 166, 1225 177, 1219 187, 1211 193, 1206 205, 1206 217, 1203 230, 1198 244, 1193 255, 1192 266, 1192 276, 1191 286, 1185 297, 1178 307, 1177 317, 1183 325, 1186 335, 1185 348, 1185 361, 1183 371, 1182 381, 1193 388, 1201 397, 1202 409, 1202 423, 1202 434, 1202 444, 1210 450, 1221 449, 1232 445, 1242 445, 1256 445, 1267 445, 1279 445, 1291 445, 1303 449, 1313 454, 1324 460, 1334 462, 1342 455, 1352 455, 1363 455, 1372 449, 1373 439, 1384 433, 1394 428, 1399 419, 1396 408, 1395 396, 1399 386, 1408 379, 1414 367, 1417 357, 1417 347, 1427 343, 1439 337, 1445 329, 1457 324, 1467 321, 1484 317, 1494 314, 1506 311, 1518 307, 1530 303, 1542 300, 1552 297, 1562 294, 1572 293, 1582 294, 1596 295, 1607 294, 1618 290, 1628 289, 1641 288, 1653 289, 1663 292, 1675 296, 1686 296, 1699 296, 1707 302, 1707 313, 1707 323, 1716 329, 1727 326, 1737 332, 1750 336, 1760 339, 1762 349, 1768 358, 1775 368, 1779 378, 1785 387, 1793 394, 1799 403, 1800 413, 1797 423, 1796 434, 1795 444, 1791 455, 1788 466, 1778 467, 1768 464, 1758 463, 1748 459, 1757 454, 1767 450, 1764 440, 1755 435, 1746 430, 1737 424, 1731 415, 1723 407, 1729 399, 1735 391, 1729 381, 1724 369, 1722 359, 1732 359, 1742 358, 1745 346, 1744 334, 1744 317, 1744 305, 1746 294, 1757 292, 1761 280, 1760 269, 1759 259, 1759 248, 1764 237, 1772 230, 1767 219, 1765 209, 1765 199, 1772 191, 1765 182, 1760 172, 1757 158, 1758 147, 1748 144, 1743 135, 1737 125, 1731 117, 1721 115, 1709 116, 1701 110, 1697 100, 1687 99, 1676 98, 1665 97, 1655 98, 1644 98, 1633 99, 1623 98, 1614 93, 1604 92, 1594 96, 1584 98, 1575 93, 1563 92, 1552 92, 1542 94, 1532 95, 1522 91, 1512 91, 1505 99, 1495 98, 1483 94, 1474 89, 1468 98, 1458 96, 1450 89, 1442 99, 1432 100, 1423 94, 1413 90, 1411 80, 1406 69, 1402 59, 1398 47, 1396 36, 1394 26, 1392 16, 1387 7, 1377 6, 1373 16, 1372 26, 1373 36, 1373 46, 1373 56, 1373 66, 1372 76, 1364 82, 1356 88, 1350 90";
        var stringCoordinatesPairsForInvalidCurve = stringCoordinatesForInvalidCurve.split(", ");
        var coordinatesForInvalidCurve = Arrays.stream(stringCoordinatesPairsForInvalidCurve).map(s -> new Coordinate(Double.parseDouble(s.split(" ")[0]), Double.parseDouble(s.split(" ")[1]))).toArray(Coordinate[]::new);
        var invalidCurve = geometryFactory.createLineString(coordinatesForInvalidCurve);

        //looking for intersections
        var curveEnvelopeBoundary = curveEnvelope.getBoundary();
        var curveIntersections = curveEnvelopeBoundary.intersection(invalidCurve);
        System.out.println(curveIntersections);



    }
}
