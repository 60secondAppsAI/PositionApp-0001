CREATE DATABASE IF NOT EXISTS PositionApp;

CREATE TABLE PositionApp.positions (
    `position_id` INT AUTO_INCREMENT,
    `portfolio_id` INT,
    `instrument_id` INT,
    `quantity` DOUBLE,
    `average_price` DOUBLE,
    `market_price` DOUBLE,
    `market_value` DOUBLE,
PRIMARY KEY (position_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE PositionApp.position_lots (
    `position_lot_id` INT AUTO_INCREMENT,
    `position_id` INT,
    `quantity` DOUBLE,
    `acquisition_price` DOUBLE,
    `acquisition_date` DATETIME,
PRIMARY KEY (position_lot_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE PositionApp.position_snapshots (
    `position_snapshot_id` INT AUTO_INCREMENT,
    `position_id` INT,
    `quantity` DOUBLE,
    `market_value` DOUBLE,
    `unrealized_pn_l` DOUBLE,
    `captured_at` DATETIME,
PRIMARY KEY (position_snapshot_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
