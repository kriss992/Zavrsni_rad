-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 19, 2019 at 03:38 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zavrsni_rad`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oib` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `ime` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `prezime` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `korisnicko_ime` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `lozinka` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `admin` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin2 COLLATE=latin2_croatian_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id`, `oib`, `ime`, `prezime`, `korisnicko_ime`, `lozinka`, `admin`) VALUES
(1, '12345678901', 'Pero', 'Perić', 'user1', 'pass1', 0),
(2, '12345678902', 'Ivo', 'Ivić', 'user2', 'pass2', 0),
(3, '12345678903', 'Marko', 'Markić', 'user3', 'pass3', 0),
(4, '12345678904', 'Andrija', 'Andrijanić', 'user4', 'pass4', 1),
(5, '12345678905', 'Nikola', 'Nikolić', 'user5', 'pass5', 0);

-- --------------------------------------------------------

--
-- Table structure for table `prijave`
--

DROP TABLE IF EXISTS `prijave`;
CREATE TABLE IF NOT EXISTS `prijave` (
  `id_prijave` int(11) NOT NULL AUTO_INCREMENT,
  `anon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `id_korisnik` int(11) NOT NULL,
  `datum` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `latituda` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `longituda` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `adresa` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `zupanija` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `poruka` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `ima_slika` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `odgovor` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `ip_korisnik` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id_prijave`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin2 COLLATE=latin2_croatian_ci;

--
-- Dumping data for table `prijave`
--

INSERT INTO `prijave` (`id_prijave`, `anon`, `id_korisnik`, `datum`, `latituda`, `longituda`, `adresa`, `zupanija`, `poruka`, `ima_slika`, `odgovor`, `ip_korisnik`) VALUES
(1, 'ne', 1, '05.08.2019. 20:44.47', '46.3237216012811', '16.288753024514346', 'Ul. Ruđera Boškovića 44, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'ne', 'Proin at pellentesque neque. Aliquam lorem est, tempor quis tristique nec, facilisis eu metus. Proin sit amet sem sed nisl eleifend sollicitudin. Quisque tristique rhoncus blandit. Proin in magna at est porta dignissim accumsan ultrices orci. Cras dui ligula, accumsan id dictum ut, congue id lacus. Donec dolor nisi, ultrices sit amet gravida nec, pulvinar eu nunc. Fusce hendrerit porta risus, ultrices faucibus est iaculis nec. Donec eu augue sed lacus malesuada viverra non a elit.  Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas vitae velit vel ligula euismod maximus. Nunc ultricies et arcu in tempor. Proin aliquet gravida finibus. Nunc dignissim, metus eu pretium auctor, lorem sem egestas tellus, in rhoncus dui odio eu tortor. Maecenas dolor lectus, efficitur vel lectus eu, ultrices viverra metus. Proin tempus, ipsum et euismod volutpat, nisi tortor feugiat tellus, non euismod arcu nulla at orci. Maecenas pharetra tortor eleifend lacus porttitor gravida.  Morbi vitae feugiat orci. Vivamus tristique pellentesque leo, non lacinia sapien posuere non. Pellentesque tempus accumsan lorem sed placerat. Nullam eget magna placerat, vestibulum dui et, ornare eros. Donec id diam dapibus est elementum hendrerit vitae ut dui. Sed sit amet felis ut nisi sollicitudin volutpat. Quisque quis felis sed diam gravida gravida. In gravida tellus id ipsum congue, sit amet consequat arcu placerat. Aliquam at erat magna. Quisque ac euismod leo. Suspendisse potenti. Praesent quis erat vitae neque convallis tincidunt at a urna. Donec commodo orci ut ligula sagittis hendrerit.', '192.168.1.103'),
(2, 'ne', 1, '05.08.2019. 20:45.24', '0.0', '0.0', '', '', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'da', 'Proin at pellentesque neque. Aliquam lorem est, tempor quis tristique nec, facilisis eu metus. Proin sit amet sem sed nisl eleifend sollicitudin. Quisque tristique rhoncus blandit. Proin in magna at est porta dignissim accumsan ultrices orci. Cras dui ligula, accumsan id dictum ut, congue id lacus. Donec dolor nisi, ultrices sit amet gravida nec, pulvinar eu nunc. Fusce hendrerit porta risus, ultrices faucibus est iaculis nec. Donec eu augue sed lacus malesuada viverra non a elit.  Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas vitae velit vel ligula euismod maximus. Nunc ultricies et arcu in tempor. Proin aliquet gravida finibus. Nunc dignissim, metus eu pretium auctor, lorem sem egestas tellus, in rhoncus dui odio eu tortor. Maecenas dolor lectus, efficitur vel lectus eu, ultrices viverra metus. Proin tempus, ipsum et euismod volutpat, nisi tortor feugiat tellus, non euismod arcu nulla at orci. Maecenas pharetra tortor eleifend lacus porttitor gravida.  Morbi vitae feugiat orci. Vivamus tristique pellentesque leo, non lacinia sapien posuere non. Pellentesque tempus accumsan lorem sed placerat. Nullam eget magna placerat, vestibulum dui et, ornare eros. Donec id diam dapibus est elementum hendrerit vitae ut dui. Sed sit amet felis ut nisi sollicitudin volutpat. Quisque quis felis sed diam gravida gravida. In gravida tellus id ipsum congue, sit amet consequat arcu placerat. Aliquam at erat magna. Quisque ac euismod leo. Suspendisse potenti. Praesent quis erat vitae neque convallis tincidunt at a urna. Donec commodo orci ut ligula sagittis hendrerit.', '192.168.1.103'),
(3, 'ne', 1, '05.08.2019. 20:45.50', '46.32373572033228', '16.28869821056234', 'Ul. Ruđera Boškovića 44, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'da', '', '192.168.1.103'),
(4, 'ne', 2, '05.08.2019. 20:46.25', '0.0', '0.0', '', '', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'ne', 'Proin at pellentesque neque. Aliquam lorem est, tempor quis tristique nec, facilisis eu metus. Proin sit amet sem sed nisl eleifend sollicitudin. Quisque tristique rhoncus blandit. Proin in magna at est porta dignissim accumsan ultrices orci. Cras dui ligula, accumsan id dictum ut, congue id lacus. Donec dolor nisi, ultrices sit amet gravida nec, pulvinar eu nunc. Fusce hendrerit porta risus, ultrices faucibus est iaculis nec. Donec eu augue sed lacus malesuada viverra non a elit.  Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas vitae velit vel ligula euismod maximus. Nunc ultricies et arcu in tempor. Proin aliquet gravida finibus. Nunc dignissim, metus eu pretium auctor, lorem sem egestas tellus, in rhoncus dui odio eu tortor. Maecenas dolor lectus, efficitur vel lectus eu, ultrices viverra metus. Proin tempus, ipsum et euismod volutpat, nisi tortor feugiat tellus, non euismod arcu nulla at orci. Maecenas pharetra tortor eleifend lacus porttitor gravida.  Morbi vitae feugiat orci. Vivamus tristique pellentesque leo, non lacinia sapien posuere non. Pellentesque tempus accumsan lorem sed placerat. Nullam eget magna placerat, vestibulum dui et, ornare eros. Donec id diam dapibus est elementum hendrerit vitae ut dui. Sed sit amet felis ut nisi sollicitudin volutpat. Quisque quis felis sed diam gravida gravida. In gravida tellus id ipsum congue, sit amet consequat arcu placerat. Aliquam at erat magna. Quisque ac euismod leo. Suspendisse potenti. Praesent quis erat vitae neque convallis tincidunt at a urna. Donec commodo orci ut ligula sagittis hendrerit.', '192.168.1.103'),
(5, 'ne', 2, '05.08.2019. 20:46.55', '46.32373320041271', '16.28869258188827', 'Ul. Ruđera Boškovića 44, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'da', 'Proin at pellentesque neque. Aliquam lorem est, tempor quis tristique nec, facilisis eu metus. Proin sit amet sem sed nisl eleifend sollicitudin. Quisque tristique rhoncus blandit. Proin in magna at est porta dignissim accumsan ultrices orci. Cras dui ligula, accumsan id dictum ut, congue id lacus. Donec dolor nisi, ultrices sit amet gravida nec, pulvinar eu nunc. Fusce hendrerit porta risus, ultrices faucibus est iaculis nec. Donec eu augue sed lacus malesuada viverra non a elit.  Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas vitae velit vel ligula euismod maximus. Nunc ultricies et arcu in tempor. Proin aliquet gravida finibus. Nunc dignissim, metus eu pretium auctor, lorem sem egestas tellus, in rhoncus dui odio eu tortor. Maecenas dolor lectus, efficitur vel lectus eu, ultrices viverra metus. Proin tempus, ipsum et euismod volutpat, nisi tortor feugiat tellus, non euismod arcu nulla at orci. Maecenas pharetra tortor eleifend lacus porttitor gravida.  Morbi vitae feugiat orci. Vivamus tristique pellentesque leo, non lacinia sapien posuere non. Pellentesque tempus accumsan lorem sed placerat. Nullam eget magna placerat, vestibulum dui et, ornare eros. Donec id diam dapibus est elementum hendrerit vitae ut dui. Sed sit amet felis ut nisi sollicitudin volutpat. Quisque quis felis sed diam gravida gravida. In gravida tellus id ipsum congue, sit amet consequat arcu placerat. Aliquam at erat magna. Quisque ac euismod leo. Suspendisse potenti. Praesent quis erat vitae neque convallis tincidunt at a urna. Donec commodo orci ut ligula sagittis hendrerit.', '192.168.1.103'),
(6, 'da', 0, '05.08.2019. 20:48.06', '46.32370104329895', '16.288651980749023', 'Ul. Ruđera Boškovića 44, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'da', '', '192.168.1.103'),
(7, 'ne', 2, '05.08.2019. 21:57.53', '0.0', '0.0', '', '', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper interdum nisl vel vulputate. Donec ut nulla non dui sodales ornare. Quisque in nunc nisi. Nullam imperdiet, massa in molestie sollicitudin, metus purus vulputate risus, ac tincidunt orci ligula non elit. Aliquam id egestas lectus, varius dictum ligula. Etiam at malesuada mauris, in pretium ante. Nulla eleifend, dolor a fermentum feugiat, lectus nulla efficitur lectus, ac egestas mi massa sit amet orci. Vestibulum elit leo, blandit at mi quis, rutrum vehicula dui. Maecenas pulvinar justo ut sem congue porttitor at nec est. Maecenas aliquam dignissim vestibulum. Quisque non nibh tincidunt, sagittis dolor non, scelerisque ex. Pellentesque ut justo hendrerit, malesuada turpis ut, malesuada mauris. Etiam in varius ligula. Sed iaculis enim ante.\nQuisque scelerisque elementum egestas. Nulla consequat elit eu imperdiet tincidunt. Praesent semper justo ut orci fringilla vestibulum in et enim. Donec et tristique velit. Proin congue auctor turpis, quis eleifend quam porta vitae. Aenean luctus lacus eget nisi euismod, id pellentesque libero luctus. Curabitur nec auctor leo. Morbi sem ligula, feugiat vitae nunc et, euismod mattis sem.\n', 'da', '', '192.168.1.103'),
(39, 'da', 0, '19.09.2019. 05:10.25', '46.323783280812215', '16.28853121459892', 'Ul. Ruđera Boškovića 44, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'žščćđ', 'ne', '', '192.168.1.103'),
(40, 'da', 0, '19.09.2019. 05:16.57', '46.323622120556955', '16.288678267286187', 'Ul. Ruđera Boškovića 42, 42209, Sračinec, Hrvatska', 'Varaždinska županija', 'žšđčć', 'ne', 'šđčćž', '192.168.1.103');

-- --------------------------------------------------------

--
-- Table structure for table `slike`
--

DROP TABLE IF EXISTS `slike`;
CREATE TABLE IF NOT EXISTS `slike` (
  `id_slika` int(11) NOT NULL AUTO_INCREMENT,
  `id_prijenos` int(11) NOT NULL,
  `slika_add` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_slika`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin2 COLLATE=latin2_croatian_ci;

--
-- Dumping data for table `slike`
--

INSERT INTO `slike` (`id_slika`, `id_prijenos`, `slika_add`) VALUES
(1, 2, 'Slika1.jpg'),
(2, 2, 'Slika2.jpg'),
(3, 2, 'Slika3.jpg'),
(4, 3, 'Slika4.jpg'),
(5, 3, 'Slika5.jpg'),
(6, 3, 'Slika6.jpg'),
(7, 3, 'Slika7.jpg'),
(8, 3, 'Slika8.jpg'),
(9, 3, 'Slika9.jpg'),
(10, 5, 'Slika10.jpg'),
(11, 5, 'Slika11.jpg'),
(12, 5, 'Slika12.jpg'),
(13, 5, 'Slika13.jpg'),
(14, 6, 'Slika14.jpg'),
(15, 6, 'Slika15.jpg'),
(16, 6, 'Slika16.jpg'),
(17, 7, 'Slika17.jpg'),
(18, 7, 'Slika18.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
