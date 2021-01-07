-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2021 at 04:06 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE project;
use project;

CREATE TABLE `bookings` (
  `uuid` varchar(100) NOT NULL,
  `clientId` varchar(100) NOT NULL,
  `venueId` varchar(100) NOT NULL,
  `eventName` varchar(100) NOT NULL,
  `noGuests` int(11) NOT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `addedOn` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`uuid`, `clientId`, `venueId`, `eventName`, `noGuests`, `startDate`, `endDate`, `addedOn`) VALUES
('2db72861-7848-48f6-ac5c-1aa2225b7058', 'medali', '21cfe5c8-339d-46eb-ade8-741599f9a341', 'Test', 200, '2020-02-20', '2020-02-21', '2021-01-07 14:53:55'),
('736f6790-7e01-4720-8fb0-acdcb678bf2d', 'test', '7ba4fee6-b831-4ea3-9ccd-18369b22baed', 'Team Building', 280, '2021-10-10', '2021-10-13', '2021-01-07 13:41:50'),
('a5fcdf8e-db97-41a9-ac20-e9e1039001c7', 'medali', '4c49ca1e-6b8a-4353-b4e3-3dd00db9be3c', 'Soccer game', 860, '2021-02-02', '2021-02-02', '2021-01-07 15:06:07'),
('d1dfd1ca-dde9-47f7-8c7f-0602637993e9', 'test', '01fd544a-5bf4-490d-b36c-c720d342becf', 'wedding', 70, '2020-10-20', '2020-10-30', '2021-01-07 13:20:45');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `age` int(10) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `picture` varchar(100) NOT NULL,
  `clientSince` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`username`, `password`, `fname`, `lname`, `age`, `gender`, `picture`, `clientSince`) VALUES
('medali', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 'medali', 'trabelsi', 21, 'Male', 'images/clients/medali.jpg', '2021-01-07 00:00:00'),
('test', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 'foulen', 'fouleni', 22, 'Male', 'images/clients/test.jpg', '2021-01-07 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `venues`
--

CREATE TABLE `venues` (
  `uuid` varchar(100) NOT NULL,
  `siteName` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `capacity` int(11) NOT NULL,
  `picture` varchar(100) NOT NULL,
  `addedOn` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `venues`
--

INSERT INTO `venues` (`uuid`, `siteName`, `city`, `address`, `type`, `capacity`, `picture`, `addedOn`) VALUES
('01fd544a-5bf4-490d-b36c-c720d342becf', 'Wedding Ballroom', 'Bizerte', 'Bizerte', 'Ballroom', 85, 'images/venues/01fd544a-5bf4-490d-b36c-c720d342becf.jpg', '2021-01-07 00:42:30'),
('21cfe5c8-339d-46eb-ade8-741599f9a341', 'Park Sidi Bou Saïd', 'Tunis', 'Sidi Bou Saïd, Carthage', 'Park/Field', 320, 'images/venues/21cfe5c8-339d-46eb-ade8-741599f9a341.jpg', '2021-01-06 22:01:43'),
('4c49ca1e-6b8a-4353-b4e3-3dd00db9be3c', 'Stade Football', 'ben Arous', 'ben Arous', 'Stadium/Arena', 1350, 'images/venues/4c49ca1e-6b8a-4353-b4e3-3dd00db9be3c.jpg', '2021-01-07 15:03:59'),
('7ba4fee6-b831-4ea3-9ccd-18369b22baed', 'Hotel el Fell', 'Nabeul', 'Hammamet', 'Hotel', 580, 'images/venues/7ba4fee6-b831-4ea3-9ccd-18369b22baed.jpg', '2021-01-07 00:45:29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `venues`
--
ALTER TABLE `venues`
  ADD PRIMARY KEY (`uuid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
