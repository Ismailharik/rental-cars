-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 06, 2023 at 05:51 PM
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
-- Database: `vehicles-service-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type_vehicle` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `description`, `type_vehicle`) VALUES
(1, 'Mini', 4),
(2, 'Economy', 2),
(3, 'Elite', 6);

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

CREATE TABLE `promo` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `reduction_percent` float NOT NULL,
  `start_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `daily_price` float NOT NULL,
  `date_first_circulation` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `franchise` float NOT NULL,
  `model` int(11) NOT NULL,
  `nbr_of_km` float NOT NULL,
  `office_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `promo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`id`, `available`, `daily_price`, `date_first_circulation`, `description`, `franchise`, `model`, `nbr_of_km`, `office_id`, `title`, `category_id`, `promo_id`) VALUES
(1, b'1', 2344, '2023-01-29 19:20:58.000000', 'range rover model 2000', 48472, 2000, 294, NULL, 'Land rover', 1, NULL),
(2, b'1', 2349, '2023-01-29 19:20:58.000000', 'mercedes amg model 2001', 48475, 2001, 294, NULL, 'mercedes amg', 2, NULL),
(3, b'1', 2354, '2023-01-29 19:20:58.000000', 'Mercedes class A  model 2002', 48478, 2002, 294, NULL, 'Mercedes class A ', 3, NULL),
(4, b'1', 2359, '2023-01-29 19:20:58.000000', '2023 Ram 2500 Heavy Duty Rebel Quick Spin: Finally, a Diesel Power Wagon (Sort Of)', 48481, 2000, 294, 4, '2023 Ram 2500 Heavy', 1, NULL),
(5, b'1', 2364, '2023-01-29 19:20:58.000000', 'Mercedes class C model 2004', 48484, 2000, 294, 5, 'range41', 2, NULL),
(6, b'1', 2369, '2023-01-29 19:20:58.000000', 'Land rover model 2005 meilleur offre', 48487, 2000, 294, 6, 'range51', 3, NULL),
(7, b'1', 2374, '2023-01-29 19:20:58.000000', '2023 Chevrolet Equinox Review: Likable — in Park', 48490, 2006, 294, 7, 'range61', 1, NULL),
(8, b'1', 2379, '2023-01-29 19:20:58.000000', 'range rover model 2007', 48493, 2007, 294, 8, 'range71', 2, NULL),
(9, b'1', 2384, '2023-01-29 19:20:58.000000', '2023 Mercedes-EQ EQB300 Review: A Solid EV With Room for Improvement', 48496, 2008, 294, 9, ' Mercedes-EQ', 3, NULL),
(10, b'1', 22, '2023-01-11 01:00:00.000000', '2022 Tesla Model Y', 4342, 2000, 23434, NULL, 'Tesla Y', 1, NULL),
(11, b'0', 334, '2023-01-18 01:00:00.000000', '2023 Ram 2500 Heavy Duty Rebel Quick Spin: Finally, a Diesel Power Wagon (Sort Of) ', 234, 2008, 22, NULL, 'Heavy Duty Rebel', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_images`
--

CREATE TABLE `vehicle_images` (
  `vehicle_id` bigint(20) NOT NULL,
  `images` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_images`
--

INSERT INTO `vehicle_images` (`vehicle_id`, `images`) VALUES
(2, '2_0.jpg'),
(3, '3_0.jpg'),
(4, '4_0.jpg'),
(5, '5_0.jpg'),
(6, '6_0.jpg'),
(7, '7_0.jpg'),
(8, '8_0.jpg'),
(9, '9_0.jpg'),
(1, '1_0.jpg'),
(1, '1_1.jpg'),
(1, '1_2.jpg'),
(1, '1_3.jpg'),
(10, '10_0.jpg'),
(11, '11_0.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_urls`
--

CREATE TABLE `vehicle_urls` (
  `vehicle_id` bigint(20) NOT NULL,
  `urls` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_urls`
--

INSERT INTO `vehicle_urls` (`vehicle_id`, `urls`) VALUES
(2, 'http://192.168.10.100:8082/vehicles/images/2/0'),
(3, 'http://192.168.10.100:8082/vehicles/images/3/0'),
(4, 'http://192.168.10.100:8082/vehicles/images/4/0'),
(5, 'http://192.168.10.100:8082/vehicles/images/5/0'),
(6, 'http://192.168.10.100:8082/vehicles/images/6/0'),
(7, 'http://192.168.10.100:8082/vehicles/images/7/0'),
(8, 'http://192.168.10.100:8082/vehicles/images/8/0'),
(9, 'http://192.168.10.100:8082/vehicles/images/9/0'),
(1, 'http://192.168.10.100:8082/vehicles/images/1/0'),
(1, 'http://localhost:8082/vehicles/images/1/1'),
(1, 'http://DESKTOP-NS3R35Q:8082/vehicles/images/1/2'),
(1, 'http://DESKTOP-NS3R35Q:8082/vehicles/images/1/3'),
(10, 'http://192.168.10.100:8082/vehicles/images/10/0'),
(11, 'http://192.168.10.100:8082/vehicles/images/11/0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq6ciksonr654eiwrshtof2m9a` (`category_id`),
  ADD KEY `FKbbw6y7golhwht7jw87pcue74m` (`promo_id`);

--
-- Indexes for table `vehicle_images`
--
ALTER TABLE `vehicle_images`
  ADD KEY `FK25kk8iwfofjgal1o383q7sqw1` (`vehicle_id`);

--
-- Indexes for table `vehicle_urls`
--
ALTER TABLE `vehicle_urls`
  ADD KEY `FKrkdrdal52ttel7ip0upug4p0f` (`vehicle_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `promo`
--
ALTER TABLE `promo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `FKbbw6y7golhwht7jw87pcue74m` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`),
  ADD CONSTRAINT `FKq6ciksonr654eiwrshtof2m9a` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `vehicle_images`
--
ALTER TABLE `vehicle_images`
  ADD CONSTRAINT `FK25kk8iwfofjgal1o383q7sqw1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`);

--
-- Constraints for table `vehicle_urls`
--
ALTER TABLE `vehicle_urls`
  ADD CONSTRAINT `FKrkdrdal52ttel7ip0upug4p0f` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
