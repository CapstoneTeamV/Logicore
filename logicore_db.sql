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

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema logicore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `logicore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `logicore` ;

-- -----------------------------------------------------
-- 1. Table `logicore`.`l_employees` (STATIC Master Data)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_employees` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_employees` (
  `emp_id` INT NOT NULL,
  `emp_code` VARCHAR(20) NOT NULL,
  `emp_name` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 2. Table `logicore`.`l_depositors` (STATIC Master Data)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_depositors` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_depositors` (
  `dpt_id` INT NOT NULL,
  `dpt_code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`dpt_id`),
  UNIQUE INDEX `dpt_code_UNIQUE` (`dpt_code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 3. Table `logicore`.`l_location` (STATIC Master Data + SHIPPED)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_location` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_location` (
  `loc_id` INT NOT NULL,
  `loc_code` VARCHAR(45) NOT NULL,
  `loc_Pack_Type` VARCHAR(3) NOT NULL,
  `loc_type` VARCHAR(4) NULL DEFAULT NULL,
  PRIMARY KEY (`loc_id`),
  UNIQUE INDEX `loc_code_UNIQUE` (`loc_code` ASC) VISIBLE,
  CONSTRAINT `chk_loc_packtype` CHECK (`loc_Pack_Type` IN ('EUR', 'QU')),
  CONSTRAINT `chk_loc_type` CHECK (`loc_type` IN ('ADR', 'SENS') OR `loc_type` IS NULL))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- 4. Table `logicore`.`l_orderstatus` (STATIC Flags)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_orderstatus` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_orderstatus` (
  `O_Status_ID` INT NOT NULL,
  `O_Status_Code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`O_Status_ID`),
  UNIQUE INDEX `O_Status_Code_UNIQUE` (`O_Status_Code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 5. Table `logicore`.`l_receiptstatus` (STATIC Flags)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_receiptstatus` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_receiptstatus` (
  `R_Status_ID` INT NOT NULL,
  `R_Status_Code` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`R_Status_ID`),
  UNIQUE INDEX `R_Status_Code_UNIQUE` (`R_Status_Code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 6. Table `logicore`.`l_truck` (STATIC Master Data)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_truck` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_truck` (
  `trk_Id` INT NOT NULL,
  `trk_code` VARCHAR(45) NOT NULL,
  `trk_area` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`trk_Id`),
  UNIQUE INDEX `trk_code_UNIQUE` (`trk_code` ASC) VISIBLE,
  UNIQUE INDEX `trk_area_UNIQUE` (`trk_area` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 7. Table `logicore`.`l_items` (DYNAMIC - 1 SSCC = 1 Παλέτα)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_items` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_items` (
  `item_id` INT NOT NULL,
  `item_SSCC` VARCHAR(25) NOT NULL,
  `item_PackType_Code` VARCHAR(3) NOT NULL,
  `item_loc_id` INT NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE INDEX `item_SSCC_UNIQUE` (`item_SSCC` ASC) VISIBLE,
  INDEX `item_PackType_Code_idx` (`item_PackType_Code` ASC) VISIBLE,
  INDEX `item_loc_id_idx` (`item_loc_id` ASC) VISIBLE,
  CONSTRAINT `chk_item_packtype` CHECK (`item_PackType_Code` IN ('EUR', 'QU')),
  CONSTRAINT `item_loc_id`
    FOREIGN KEY (`item_loc_id`)
    REFERENCES `logicore`.`l_location` (`loc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 8. Table `logicore`.`l_receipts` (DYNAMIC - Inbound)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_receipts` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_receipts` (
  `rct_id` INT NOT NULL,
  `rct_code` VARCHAR(20) NOT NULL,
  `rct_input_date` DATE NOT NULL,
  `rct_quantity` INT NOT NULL DEFAULT 1,
  `rct_status_Id` INT NOT NULL,
  `rct_Item_id` INT NOT NULL,
  `rct_truck_id` INT NOT NULL,
  `rct_location_id` INT NOT NULL,
  `rct_emp_id` INT NOT NULL,
  `rct_dpt_id` INT NOT NULL,
  PRIMARY KEY (`rct_id`),
  UNIQUE INDEX `Receipt_Code_UNIQUE` (`rct_code` ASC) VISIBLE,
  INDEX `Receipt_Status_Id_idx` (`rct_status_Id` ASC) VISIBLE,
  INDEX `Receipt_Item_Id_idx` (`rct_Item_id` ASC) VISIBLE,
  INDEX `Receipt_Truck_Id_idx` (`rct_truck_id` ASC) VISIBLE,
  INDEX `rct_location_id_idx` (`rct_location_id` ASC) VISIBLE,
  INDEX `rct_emp_id_idx` (`rct_emp_id` ASC) VISIBLE,
  INDEX `rct_dpt_id_idx` (`rct_dpt_id` ASC) VISIBLE,
  CONSTRAINT `chk_rct_qty` CHECK (`rct_quantity` = 1),
  CONSTRAINT `rct_emp_id`
    FOREIGN KEY (`rct_emp_id`)
    REFERENCES `logicore`.`l_employees` (`emp_id`),
  CONSTRAINT `rct_location_id`
    FOREIGN KEY (`rct_location_id`)
    REFERENCES `logicore`.`l_location` (`loc_id`),
  CONSTRAINT `Receipt_Item_Id`
    FOREIGN KEY (`rct_Item_id`)
    REFERENCES `logicore`.`l_items` (`item_id`),
  CONSTRAINT `Receipt_Status_Id`
    FOREIGN KEY (`rct_status_Id`)
    REFERENCES `logicore`.`l_receiptstatus` (`R_Status_ID`),
  CONSTRAINT `Receipt_Truck_Id`
    FOREIGN KEY (`rct_truck_id`)
    REFERENCES `logicore`.`l_truck` (`trk_Id`),
  CONSTRAINT `rct_dpt_id`
    FOREIGN KEY (`rct_dpt_id`)
    REFERENCES `logicore`.`l_depositors` (`dpt_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- 9. Table `logicore`.`l_orders` (DYNAMIC - Outbound)
-- -----------------------------------------------------
DROP TABLE IF EXISTS `logicore`.`l_orders` ;

CREATE TABLE IF NOT EXISTS `logicore`.`l_orders` (
  `ord_id` INT NOT NULL,
  `ord_code` VARCHAR(20) NOT NULL,
  `ord_output_date` DATE NOT NULL,
  `ord_quantity` INT NOT NULL DEFAULT 1,
  `ord_status_id` INT NOT NULL,
  `ord_Item_id` INT NOT NULL,
  `ord_truck_id` INT NOT NULL,
  `ord_loc_id` INT NOT NULL,
  `ord_emp_id` INT NOT NULL,
  `ord_dpt_id` INT NOT NULL,
  PRIMARY KEY (`ord_id`),
  UNIQUE INDEX `Order_Code_UNIQUE` (`ord_code` ASC) VISIBLE,
  INDEX `Order_Status_Id_idx` (`ord_status_id` ASC) VISIBLE,
  INDEX `Orders_Truck_Id_idx` (`ord_truck_id` ASC) VISIBLE,
  INDEX `ord_loc_id_idx` (`ord_loc_id` ASC) VISIBLE,
  INDEX `ord_emp_id_idx` (`ord_emp_id` ASC) VISIBLE,
  INDEX `Orders_Item_Id_idx` (`ord_Item_id` ASC) VISIBLE,
  INDEX `ord_dpt_id_idx` (`ord_dpt_id` ASC) VISIBLE,
  CONSTRAINT `chk_ord_qty` CHECK (`ord_quantity` = 1),
  CONSTRAINT `ord_emp_id`
    FOREIGN KEY (`ord_emp_id`)
    REFERENCES `logicore`.`l_employees` (`emp_id`),
  CONSTRAINT `ord_loc_id`
    FOREIGN KEY (`ord_loc_id`)
    REFERENCES `logicore`.`l_location` (`loc_id`),
  CONSTRAINT `Order_Status_Id`
    FOREIGN KEY (`ord_status_id`)
    REFERENCES `logicore`.`l_orderstatus` (`O_Status_ID`),
  CONSTRAINT `Orders_Item_Id`
    FOREIGN KEY (`ord_Item_id`)
    REFERENCES `logicore`.`l_items` (`item_id`),
  CONSTRAINT `Orders_Truck_Id`
    FOREIGN KEY (`ord_truck_id`)
    REFERENCES `logicore`.`l_truck` (`trk_Id`),
  CONSTRAINT `ord_dpt_id`
    FOREIGN KEY (`ord_dpt_id`)
    REFERENCES `logicore`.`l_depositors` (`dpt_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- =====================================================
-- ΑΡΧΙΚΑ STATIC ΔΕΔΟΜΕΝΑ
-- =====================================================

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

INSERT INTO `l_employees` (`emp_id`, `emp_code`, `emp_name`) VALUES
(1, 'Admin', 'Κώστας Σπυρόπουλος'),
(2, 'Υπάλληλος', 'Αλέξης Χατζηγεωργίου'),
(3, 'Αποθηκάριος', 'Abdul Mosad'),
(4, 'Διευθυντής', 'Γιώργος Παπατζής');

INSERT INTO `l_depositors` (`dpt_id`, `dpt_code`) VALUES
(1, 'HomeMarkt'),
(2, 'SpeChem'),
(3, 'Interplast Α.Ε.');

INSERT INTO `l_truck` (`trk_Id`, `trk_code`, `trk_area`) VALUES
(1, 'NKB1234', '54622'),
(2, 'IKM5678', '10431'),
(3, 'ZAE9012', '41222');

INSERT INTO `logicore`.`l_location` (`loc_id`, `loc_code`, `loc_Pack_Type`, `loc_type`)
SELECT 
    (z.n * 10000) + (p.n * 100) + f.n AS loc_id,
    CONCAT(z.n, '-', LPAD(p.n, 2, '0'), '-', LPAD(f.n, 2, '0')) AS loc_code,
    'EUR' AS loc_Pack_Type,
    CASE 
        WHEN z.n = 5 THEN 'ADR'
        WHEN z.n = 4 AND p.n <= 5 THEN 'SENS'
        ELSE NULL 
    END AS loc_type
FROM 
    (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) z
CROSS JOIN 
    (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) p
CROSS JOIN 
    (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) f;
     
     
