CREATE SCHEMA IF NOT EXISTS `logicore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `logicore`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `l_suppliers`;

-- -----------------------------------------------------
-- 1. Table `logicore`.`l_orderstatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_orderstatus`;
CREATE TABLE `l_orderstatus` (
  `O_Status_ID` INT NOT NULL,
  `O_Status_Code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`O_Status_ID`),
  UNIQUE INDEX `O_Status_Code_UNIQUE` (`O_Status_Code` DESC) VISIBLE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 2. Table `logicore`.`l_receiptstatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_receiptstatus`;
CREATE TABLE `l_receiptstatus` (
  `R_Status_ID` INT NOT NULL,
  `R_Status_Code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`R_Status_ID`),
  UNIQUE INDEX `R_Status_Code_UNIQUE` (`R_Status_Code` desc) VISIBLE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 3. Table `logicore`.`l_employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_employees`;
CREATE TABLE `l_employees` (
  `emp_id` INT NOT NULL,
  `emp_code` VARCHAR(20) NOT NULL,
  `emp_name` VARCHAR(60) NULL,
  `emp_ord_status_id` INT NOT NULL,
  `emp_rct_status_id` INT NOT NULL,
  PRIMARY KEY (`emp_id`),
  INDEX `emp_ord_status_id_idx` (`emp_ord_status_id` desc) VISIBLE,
  INDEX `emp_rct_status_id_idx` (`emp_rct_status_id` desc) VISIBLE,
  CONSTRAINT `l_employees_ibfk_1`
    FOREIGN KEY (`emp_ord_status_id`)
    REFERENCES `l_orderstatus` (`O_Status_ID`),
  CONSTRAINT `l_employees_ibfk_2`
    FOREIGN KEY (`emp_rct_status_id`)
    REFERENCES `l_receiptstatus` (`R_Status_ID`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 4. Table `logicore`.`l_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_products`;
CREATE TABLE `l_products` (
  `prd_ID` INT NOT NULL,
  `prd_code` VARCHAR(20) NOT NULL,
  `Prd_SSCC` VARCHAR(25) NOT NULL,
  `prd_PackType_Code` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`prd_ID`),
  UNIQUE INDEX `prd_code_UNIQUE` (`prd_code` ASC) VISIBLE,
  UNIQUE INDEX `Prd_SSCC_UNIQUE` (`Prd_SSCC` ASC) VISIBLE,
  INDEX `idx_prd_PackType_Code` (`prd_PackType_Code` ASC) VISIBLE,
  CONSTRAINT `chk_prd_packtype` CHECK (`prd_PackType_Code` IN ('EU', 'ST'))
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 5. Table `logicore`.`l_truck`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_truck`;
CREATE TABLE `l_truck` (
  `trk_Id` INT NOT NULL,
  `trk_code` VARCHAR(45) NOT NULL,
  `trk_area` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`trk_Id`),
  UNIQUE INDEX `trk_code_UNIQUE` (`trk_code` ASC) VISIBLE,
  UNIQUE INDEX `trk_area_UNIQUE` (`trk_area` ASC) VISIBLE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 6. Table `logicore`.`l_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_location`;
CREATE TABLE `l_location` (
  `loc_id` INT NOT NULL,
  `loc_code` VARCHAR(45) NOT NULL,
  `loc_Pack_Type` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`loc_id`),
  UNIQUE INDEX `loc_code_UNIQUE` (`loc_code` ASC) VISIBLE,
  INDEX `loc_Pack_Type_idx` (`loc_Pack_Type` ASC) VISIBLE,
  CONSTRAINT `chk_loc_packtype` CHECK (`loc_Pack_Type` IN ('EU', 'ST')),
  CONSTRAINT `loc_Pack_Type`
    FOREIGN KEY (`loc_Pack_Type`)
    REFERENCES `l_products` (`prd_PackType_Code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 7. Table `logicore`.`l_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_orders`;
CREATE TABLE `l_orders` (
  `ord_id` INT NOT NULL,
  `ord_code` VARCHAR(20) NOT NULL,
  `ord_status_id` INT NOT NULL,
  `ord_prd_id` INT NOT NULL,
  `ord_truck_id` INT NOT NULL,
  `ord_loc_id` INT NOT NULL,
  `ord_emp_id` INT NOT NULL,
  PRIMARY KEY (`ord_id`),
  UNIQUE INDEX `Order_Code_UNIQUE` (`ord_code` ASC) VISIBLE,
  INDEX `Order_Status_Id_idx` (`ord_status_id` ASC) VISIBLE,
  INDEX `Orders_Prd_Id_idx` (`ord_prd_id` ASC) VISIBLE,
  INDEX `Orders_Truck_Id_idx` (`ord_truck_id` ASC) VISIBLE,
  INDEX `ord_loc_id_idx` (`ord_loc_id` ASC) VISIBLE,
  INDEX `ord_emp_id_idx` (`ord_emp_id` ASC) VISIBLE,
  CONSTRAINT `Order_Status_Id`
    FOREIGN KEY (`ord_status_id`)
    REFERENCES `l_orderstatus` (`O_Status_ID`),
  CONSTRAINT `Orders_Prd_Id`
    FOREIGN KEY (`ord_prd_id`)
    REFERENCES `l_products` (`prd_ID`),
  CONSTRAINT `Orders_Truck_Id`
    FOREIGN KEY (`ord_truck_id`)
    REFERENCES `l_truck` (`trk_Id`),
  CONSTRAINT `ord_loc_id`
    FOREIGN KEY (`ord_loc_id`)
    REFERENCES `l_location` (`loc_id`),
  CONSTRAINT `ord_emp_id`
    FOREIGN KEY (`ord_emp_id`)
    REFERENCES `l_employees` (`emp_id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 8. Table `logicore`.`l_receipts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_receipts`;
CREATE TABLE `l_receipts` (
  `rct_id` INT NOT NULL,
  `rct_code` VARCHAR(20) NOT NULL,
  `rct_status_Id` INT NOT NULL,
  `rct_prd_id` INT NOT NULL,
  `rct_truck_id` INT NOT NULL,
  `rct_location_id` INT NOT NULL,
  `rct_emp_id` INT NOT NULL,
  PRIMARY KEY (`rct_id`),
  UNIQUE INDEX `Receipt_Code_UNIQUE` (`rct_code` ASC) VISIBLE,
  INDEX `Receipt_Status_Id_idx` (`rct_status_Id` ASC) VISIBLE,
  INDEX `Receipt_Prd_Id_idx` (`rct_prd_id` ASC) VISIBLE,
  INDEX `Receipt_Truck_Id_idx` (`rct_truck_id` ASC) VISIBLE,
  INDEX `rct_location_id_idx` (`rct_location_id` ASC) VISIBLE,
  INDEX `rct_emp_id_idx` (`rct_emp_id` ASC) VISIBLE,
  CONSTRAINT `Receipt_Status_Id`
    FOREIGN KEY (`rct_status_Id`)
    REFERENCES `l_receiptstatus` (`R_Status_ID`),
  CONSTRAINT `Receipt_Prd_Id`
    FOREIGN KEY (`rct_prd_id`)
    REFERENCES `l_products` (`prd_ID`),
  CONSTRAINT `Receipt_Truck_Id`
    FOREIGN KEY (`rct_truck_id`)
    REFERENCES `l_truck` (`trk_Id`),
  CONSTRAINT `rct_location_id`
    FOREIGN KEY (`rct_location_id`)
    REFERENCES `l_location` (`loc_id`),
  CONSTRAINT `rct_emp_id`
    FOREIGN KEY (`rct_emp_id`)
    REFERENCES `l_employees` (`emp_id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 9. Table `logicore`.`l_depositors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `l_depositors`;
CREATE TABLE `l_depositors` (
  `dpt_id` INT NOT NULL,
  `dpt_code` VARCHAR(20) NOT NULL,
  `dpt_ord_id` INT NOT NULL,
  `dpt_rct_id` INT NOT NULL,
  PRIMARY KEY (`dpt_id`),
  INDEX `Customer_Ord_Id_idx` (`dpt_ord_id` ASC) VISIBLE,
  INDEX `Depostior_Rct_Id_idx` (`dpt_rct_id` ASC) VISIBLE,
  CONSTRAINT `Customer_Ord_Id`
    FOREIGN KEY (`dpt_ord_id`)
    REFERENCES `l_orders` (`ord_id`),
  CONSTRAINT `Depostior_Rct_Id`
    FOREIGN KEY (`dpt_rct_id`)
    REFERENCES `l_receipts` (`rct_id`)
) ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Αρχικά Δεδομένα Status & Ρόλων Χρηστών
-- -----------------------------------------------------
INSERT INTO `l_orderstatus` (`O_Status_ID`, `O_Status_Code`) VALUES
(1, 'Σε διεκπεραίωση'),
(2, 'Ελέγχθηκε'),
(3, 'Προς φόρτωση'),
(4, 'Ολοκληρώθηκε'),
(5, 'Ακυρώθηκε');

INSERT INTO `l_receiptstatus` (`R_Status_ID`, `R_Status_Code`) VALUES
(1, 'Σε αναμονή'),
(2, 'Παραλήφθηκε'),
(3, 'Ακυρώθηκε');

INSERT INTO `l_employees` (`emp_id`, `emp_code`, `emp_name`, `emp_ord_status_id`, `emp_rct_status_id`) VALUES
(1, 'Admin', NULL, 1, 1),
(2, 'Υπάλληλος', NULL, 1, 1),
(3, 'Αποθηκάριος', NULL, 1, 1),
(4, 'Διευθυντής', NULL, 1, 1);
