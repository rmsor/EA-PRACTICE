-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2014 at 06:40 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `onlinepos`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `ID` bigint(20) NOT NULL,
  `CATEGORY_NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `TAX_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `CATEGORY_NAME`, `DESCRIPTION`, `PARENT_ID`, `TAX_ID`) VALUES
(104, 'Electronics', 'Electronics goods', 0, 102),
(105, 'Computer Accesories', 'Computer goods', 0, 102),
(106, 'Soft Drinks', 'Drinking goods', 0, 102),
(107, 'Cosmetics', 'Beauty products', 0, 102);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` bigint(20) NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `contact_number1` varchar(255) DEFAULT NULL,
  `customer_number2` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `ID` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `group_fk` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `EMAIL`, `FNAME`, `LNAME`, `PASSWORD`, `PHONE`, `USERNAME`, `group_fk`) VALUES
(1, 's@gmail.com', 'Sadakul', 'Islam', 'sadak', '1234', 'sadak', 651),
(51, 'm@gmail.com', 'Md Mahbubur', 'Rahman', 'mahbub', '1234', 'mahbub', 652),
(52, 'a@gmail.com', 'Anwar ', 'Jahan', 'anwar', '1234', 'anwar', 652),
(101, 'z@gmail.com', 'Md Zahidul', 'Islam', 'zahid', '6414514580', 'zahid', 653);

-- --------------------------------------------------------

--
-- Table structure for table `groupofrole`
--

CREATE TABLE IF NOT EXISTS `groupofrole` (
  `ID` bigint(20) NOT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupofrole`
--

INSERT INTO `groupofrole` (`ID`, `ROLENAME`) VALUES
(651, 'Admin'),
(652, 'Manager'),
(653, 'Cashier');

-- --------------------------------------------------------

--
-- Table structure for table `orderinfo`
--

CREATE TABLE IF NOT EXISTS `orderinfo` (
  `ID` bigint(20) NOT NULL,
  `TAX` double DEFAULT NULL,
  `TOTALPRICE` double DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderinfo`
--

INSERT INTO `orderinfo` (`ID`, `TAX`, `TOTALPRICE`, `customer_id`) VALUES
(157, 0, 218.36, NULL),
(162, 0, 1277.2, NULL),
(167, 0, 10.3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orderline`
--

CREATE TABLE IF NOT EXISTS `orderline` (
  `orderline_id` bigint(20) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SUBTOTAL` double DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderline`
--

INSERT INTO `orderline` (`orderline_id`, `QUANTITY`, `SUBTOTAL`, `order_id`, `product_id`) VALUES
(158, 2, 0, 157, 108),
(159, 3, 0, 157, 155),
(163, 4, 0, 162, 153),
(164, 3, 0, 162, 154),
(168, 5, 0, 167, 156);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `ID` bigint(20) NOT NULL,
  `AMOUNT` double DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TRANSACTION_DATE` date DEFAULT NULL,
  `paymenttyep_fk` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`ID`, `AMOUNT`, `COMMENTS`, `ORDER_ID`, `STATUS`, `TRANSACTION_DATE`, `paymenttyep_fk`) VALUES
(161, 218.36, 'first final test', 157, 'sucess', '2014-12-16', 160),
(166, 1277.2, 'finalTest cash', 162, 'sucess', '2014-12-16', 165),
(170, 10.3, 'multiple order Test', 167, 'sucess', '2014-12-16', 169);

-- --------------------------------------------------------

--
-- Table structure for table `paymenttype`
--

CREATE TABLE IF NOT EXISTS `paymenttype` (
  `ID` bigint(20) NOT NULL,
  `METHOD` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymenttype`
--

INSERT INTO `paymenttype` (`ID`, `METHOD`, `TYPE`) VALUES
(160, 'card', 'americanexpress'),
(165, 'cash', NULL),
(169, 'card', 'visa');

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE IF NOT EXISTS `permission` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PERMISSION_NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `permissiontype`
--

CREATE TABLE IF NOT EXISTS `permissiontype` (
  `ID` bigint(20) NOT NULL,
  `PERMISSION_OPTION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ID` bigint(20) NOT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `PRODUCT_DESC` varchar(255) DEFAULT NULL,
  `PRODUCT_IMAGE` varchar(255) DEFAULT NULL,
  `PRODUCT_NAME` varchar(255) DEFAULT NULL,
  `PRODUCT_TYPE` varchar(255) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SELLING_PRICE` double DEFAULT NULL,
  `SUPPLIER_UNIT_PRICE` double DEFAULT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `SUPPLIER_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `DISCOUNT`, `PRODUCT_DESC`, `PRODUCT_IMAGE`, `PRODUCT_NAME`, `PRODUCT_TYPE`, `QUANTITY`, `SELLING_PRICE`, `SUPPLIER_UNIT_PRICE`, `CATEGORY_ID`, `SUPPLIER_ID`) VALUES
(108, 0, 'Room Heater', 'shopping.jpg', 'Heater', NULL, 500, 103, 100, 104, 103),
(151, 0, 'Philips Microwave', '103_0304.JPG', 'Microwave', NULL, 300, 206, 200, 104, 103),
(153, 0, 'Logitect Mouse', '19689.png', 'Mouse', NULL, 300, 10.3, 10, 105, 103),
(154, 0, 'Intel Motherboard', 'motherboard.jpg', 'MotherBoard', NULL, 100, 412, 400, 105, 103),
(155, 0, 'Beauty Soap', 'dove_beauty_bar.jpg', 'Dove', NULL, 1000, 4.12, 4, 107, 103),
(156, 0, 'Soft drinks', 'mountain.jpg', 'Mountain Dew', NULL, 500, 2.06, 2, 106, 103);

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '250');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `ID` bigint(20) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CONTACT1` varchar(255) DEFAULT NULL,
  `CONTACT2` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `SUPPLIER_NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`ID`, `ADDRESS`, `CONTACT1`, `CONTACT2`, `EMAIL`, `SUPPLIER_NAME`) VALUES
(103, 'room#310', '123', '123', 'ipel@gmail.com', 'Iqbal Ipel');

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE IF NOT EXISTS `tax` (
  `ID` bigint(20) NOT NULL,
  `PERCENTAGE` int(11) DEFAULT NULL,
  `TAX_DESC` varchar(255) DEFAULT NULL,
  `TAX_NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tax`
--

INSERT INTO `tax` (`ID`, `PERCENTAGE`, `TAX_DESC`, `TAX_NAME`) VALUES
(102, 3, 'Iowa State Tax', 'State Tax');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`ID`), ADD KEY `FK_CATEGORY_TAX_ID` (`TAX_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
 ADD PRIMARY KEY (`ID`), ADD KEY `FK_EMPLOYEE_group_fk` (`group_fk`);

--
-- Indexes for table `groupofrole`
--
ALTER TABLE `groupofrole`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `orderinfo`
--
ALTER TABLE `orderinfo`
 ADD PRIMARY KEY (`ID`), ADD KEY `FK_ORDERINFO_customer_id` (`customer_id`);

--
-- Indexes for table `orderline`
--
ALTER TABLE `orderline`
 ADD PRIMARY KEY (`orderline_id`), ADD KEY `FK_ORDERLINE_order_id` (`order_id`), ADD KEY `FK_ORDERLINE_product_id` (`product_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
 ADD PRIMARY KEY (`ID`), ADD KEY `FK_PAYMENT_paymenttyep_fk` (`paymenttyep_fk`);

--
-- Indexes for table `paymenttype`
--
ALTER TABLE `paymenttype`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `permissiontype`
--
ALTER TABLE `permissiontype`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`ID`), ADD KEY `FK_PRODUCT_CATEGORY_ID` (`CATEGORY_ID`), ADD KEY `FK_PRODUCT_SUPPLIER_ID` (`SUPPLIER_ID`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
 ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tax`
--
ALTER TABLE `tax`
 ADD PRIMARY KEY (`ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
ADD CONSTRAINT `FK_CATEGORY_TAX_ID` FOREIGN KEY (`TAX_ID`) REFERENCES `tax` (`ID`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
ADD CONSTRAINT `FK_EMPLOYEE_group_fk` FOREIGN KEY (`group_fk`) REFERENCES `groupofrole` (`ID`);

--
-- Constraints for table `orderinfo`
--
ALTER TABLE `orderinfo`
ADD CONSTRAINT `FK_ORDERINFO_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `orderline`
--
ALTER TABLE `orderline`
ADD CONSTRAINT `FK_ORDERLINE_order_id` FOREIGN KEY (`order_id`) REFERENCES `orderinfo` (`ID`),
ADD CONSTRAINT `FK_ORDERLINE_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`ID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
ADD CONSTRAINT `FK_PAYMENT_paymenttyep_fk` FOREIGN KEY (`paymenttyep_fk`) REFERENCES `paymenttype` (`ID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
ADD CONSTRAINT `FK_PRODUCT_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`),
ADD CONSTRAINT `FK_PRODUCT_SUPPLIER_ID` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `supplier` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
