-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: sql301.byetcluster.com
-- Generation Time: Mar 24, 2015 at 04:38 AM
-- Server version: 5.6.22-71.0
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `b33_15999105_myeten`
--

-- --------------------------------------------------------

--
-- Table structure for table `2011_cse_a`
--


-- --------------------------------------------------------

--
-- Table structure for table `absentees`
--

CREATE TABLE IF NOT EXISTS `absentees` (
  `fphno` bigint(10) NOT NULL,
  `abs_list` varchar(500) NOT NULL,
  `batch` int(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `sec` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `period` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `absentees`
--

INSERT INTO `absentees` (`fphno`, `abs_list`, `batch`, `branch`, `sec`, `date`, `period`) VALUES
(1234567890, '"01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02"', 2011, 'cse', 'a', '2015-03-05', 3),
(1234567890, '"01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02"', 2011, 'ece', 'a', '2015-03-05', 3),
(1234567890, '"01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","01","01","01"', 2011, 'ece', 'b', '2015-03-05', 4),
(1234567890, '"01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","02","01","01","01","01","01","02"', 2011, 'ece', 'b', '2015-03-05', 4),
(1234567890, '%2204%22%2C%2205%22', 2011, 'cse', 'a', '2015-03-23', 5),
(1234567890, '%2202%22%2C%2205%22', 2011, 'mechanical', 'a', '2015-03-23', 7),
(1234567890, '%2203%22%2C%2205%22', 2011, 'civil', 'a', '2015-03-23', 7),
(1234567890, '%2204%22%2C%2205%22', 2011, 'cse', 'a', '2015-03-23', 3),
(1234567890, '%2202%22%2C%2203%22%2C%2204%22', 2013, 'cse', 'a', '2015-03-22', 3),
(1234567890, '', 2011, 'cse', 'b', '2015-03-05', 4),
(1234567890, '"01","02"', 2011, 'cse', 'b', '2015-03-05', 4),
(1234567890, '"02","03"', 2011, 'cse', 'b', '2015-03-05', 4),
(1234567890, '"01","02","03"', 2011, 'ece', 'a', '2015-03-06', 8),
(1234567890, '"11","12"', 2011, 'CSE', 'A', '2015-03-14', 4),
(1234567890, '"04","11"', 2011, 'cse', 'a', '2015-03-21', 1),
(1234567890, '%2205%22', 2011, 'cse', 'a', '2015-03-22', 3),
(1234567890, '%2201%22%2C%2202%22%2C%2203%22%2C%2204%22', 2012, 'cse', 'a', '2015-03-22', 3);

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `phno` bigint(12) NOT NULL,
  `code` bigint(20) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`phno`),
  UNIQUE KEY `uname` (`uname`),
  UNIQUE KEY `password` (`password`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`phno`, `code`, `uname`, `password`) VALUES
(1234567890, 68656, 'myetendroid', '*********');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `fid` varchar(20) NOT NULL,
  `fname` text NOT NULL,
  `phno` bigint(12) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `batch` int(5) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `sec` varchar(5) NOT NULL,
  `code` bigint(12) NOT NULL,
  PRIMARY KEY (`fid`),
  UNIQUE KEY `phno` (`phno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`fid`, `fname`, `phno`, `designation`, `batch`, `branch`, `sec`, `code`) VALUES
('65', 'V.Kasturi', 1234567890, 'HOD', 0, 'CSE', '', 10843),
('FacEc19', 'Srikanth.Vnv', 1234567890, 'CLASS_TEACHER', 2012, 'ECE', 'B', 1234567890),
('FacIt20', 'Prathyusha.V.L', 1234567890, 'FACULTY', 0, '', '', 1234567890),
('131', 'yasodhar', 1234567890, 'FACULTY', 0, '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `rollno` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `batch` int(5) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `sec` varchar(10) NOT NULL,
  `phno` bigint(12) NOT NULL,
  PRIMARY KEY (`rollno`),
  UNIQUE KEY `phno` (`phno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`rollno`, `name`, `batch`, `branch`, `sec`, `phno`) VALUES
(11, 'V.L.Prathyusha', 2011, 'CSE', 'A', 1234567890),
(12, 'surya', 2011, 'CSE', 'A', 1234567890);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
