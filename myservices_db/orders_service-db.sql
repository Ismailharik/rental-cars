-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 06, 2023 at 05:09 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orders_service-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `confirmed` bit(1) NOT NULL,
  `duration` int(11) NOT NULL,
  `is_paid` bit(1) NOT NULL,
  `office_id` int(11) NOT NULL,
  `pick_up_date` datetime(6) DEFAULT NULL,
  `total_price` float NOT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `customer_id`, `confirmed`, `duration`, `is_paid`, `office_id`, `pick_up_date`, `total_price`, `vehicle_id`) VALUES
(1, 1, b'0', 3, b'0', 1, '2023-02-05 22:16:47.000000', 10, 1),
(2, 2, b'0', 3, b'0', 2, '2023-02-05 22:16:47.000000', 20, 2),
(3, 3, b'0', 3, b'0', 3, '2023-02-05 22:16:47.000000', 30, 3),
(4, 4, b'0', 3, b'0', 4, '2023-02-05 22:16:47.000000', 40, 4),
(5, 5, b'0', 3, b'0', 5, '2023-02-05 22:16:47.000000', 50, 5),
(6, 6, b'0', 3, b'0', 6, '2023-02-05 22:16:47.000000', 60, 6),
(7, 7, b'0', 3, b'0', 7, '2023-02-05 22:16:47.000000', 70, 7),
(8, 8, b'0', 3, b'0', 8, '2023-02-05 22:16:47.000000', 80, 8),
(9, 9, b'0', 3, b'0', 9, '2023-02-05 22:16:47.000000', 90, 9),
(10, 10, b'0', 3, b'0', 10, '2023-02-05 22:16:47.000000', 100, 10);

-- --------------------------------------------------------

--
-- Table structure for table `stock_feedback`
--

CREATE TABLE `stock_feedback` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock_feedback`
--

INSERT INTO `stock_feedback` (`id`, `date`, `total_price`) VALUES
(1, '2001-01-01', 0),
(2, '2002-02-02', 118),
(3, '2003-03-03', 236),
(4, '2004-04-04', 354),
(5, '2005-05-05', 472),
(6, '2006-06-06', 590),
(7, '2007-07-07', 708),
(8, '2008-08-08', 826),
(9, '2009-09-09', 944),
(10, '2010-10-10', 1062);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_feedback`
--
ALTER TABLE `stock_feedback`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `stock_feedback`
--
ALTER TABLE `stock_feedback`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
