-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2014 at 10:25 PM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `promed`
--

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) NOT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`, `group_desc`) VALUES
(1, 'ADMIN', 'Access for admins'),
(2, 'DOCTOR', 'Access for Doctors'),
(3, 'NURSE', 'Access for Nurse'),
(4, 'USER', 'Access for clients / patients');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESSES` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `SSN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`ID`, `ADDRESSES`, `FIRSTNAME`, `LASTNAME`, `MOBILE`, `PHONE`, `SSN`) VALUES
(1, NULL, 'Kundan', 'Parajuli', NULL, NULL, NULL),
(2, NULL, 'Doctor Name', 'Doctor Last Name', NULL, NULL, NULL),
(3, NULL, 'Nurse Name', 'Nurse Last Name', NULL, NULL, NULL),
(4, NULL, 'User Name', 'User Last Name', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `VERIFICATION` varchar(255) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK_USERS_PERSON_ID` (`PERSON_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USER_ID`, `EMAIL`, `PASSWORD`, `USERNAME`, `VERIFICATION`, `PERSON_ID`) VALUES
(1, 'kundanparajuli@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', NULL, 1),
(2, 'kundanparajuli@gmail.com', '72f4be89d6ebab1496e21e38bcd7c8ca0a68928af3081ad7dff87e772eb350c2', 'doctor', NULL, 2),
(3, 'kundanparajuli@gmail.com', '781e5116a1e14a34eada50159d589e690c81ec4c5063115ea1f10b99441d5b94', 'nurse', NULL, 3),
(4, 'kundanparajuli@gmail.com', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user_groups`
--

DROP TABLE IF EXISTS `user_groups`;
CREATE TABLE IF NOT EXISTS `user_groups` (
  `user_id` int(10) NOT NULL,
  `group_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `fk_users_has_groups_groups1` (`group_id`),
  KEY `fk_users_has_groups_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_groups`
--

INSERT INTO `user_groups` (`user_id`, `group_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_user_role`
--
DROP VIEW IF EXISTS `v_user_role`;
CREATE TABLE IF NOT EXISTS `v_user_role` (
`username` varchar(255)
,`password` varchar(255)
,`group_name` varchar(20)
);
-- --------------------------------------------------------

--
-- Structure for view `v_user_role`
--
DROP TABLE IF EXISTS `v_user_role`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`mysql.cfrk67jbhkou.us-east-1.rds.amazonaws.com` SQL SECURITY DEFINER VIEW `v_user_role` AS select `u`.`USERNAME` AS `username`,`u`.`PASSWORD` AS `password`,`g`.`group_name` AS `group_name` from ((`user_groups` `ug` join `users` `u` on((`u`.`USER_ID` = `ug`.`user_id`))) join `groups` `g` on((`g`.`group_id` = `ug`.`group_id`)));

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_USERS_PERSON_ID` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`);

--
-- Constraints for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `fk_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_user_groups_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
