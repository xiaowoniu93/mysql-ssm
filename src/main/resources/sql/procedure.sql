-- 定义存储过程
-- 参数：in 输入参数；out 输出参数
-- ROW_COUNT():返回上一条sql(insert、update、delete)的影响行数
-- ROW_COUNT():结果 0:未修改数据; >0:影响的行数; <0:sql错误/未执行
DELIMITER $$
CREATE PROCEDURE seckill.execute_seckill(
	in v_seckill_id BIGINT,
	in v_phone VARCHAR,
	in v_kill_time datetime,
	out r_result INT
)
BEGIN
	DECLARE insert_count INT DEFAULT 0;
	START TRANSACTION;
	INSERT IGNORE INTO sk_success_log
		(pro_stock_id, user_phone, state, add_time)
	VALUES
		(v_seckill_id, v_phone, 0, v_kill_time);
	SELECT ROW_COUNT() INTO insert_count;
	IF(insert_count = 0) THEN
		ROLLBACK;
		SET r_result = -1;
	ELSEIF(insert_count < 0) THEN
		ROLLBACK;
		SET r_result = -2;
	ELSE
		UPDATE
			sk_product_stock
		SET
			number = number - 1
		WHERE
			id = v_seckill_id
		AND	start_time <= v_kill_time
		AND end_time >= v_kill_time
		AND number > 0;
		SELECT ROW_COUNT() INTO insert_count;
		IF(insert_count = 0) THEN
			ROLLBACK;
			SET r_result = 0;
		ELSEIF(insert_count < 0) THEN
			ROLLBACK;
			SET r_result = -2;
		ELSE
			COMMIT;
			SET r_result = 1;
		END IF;
	END IF;
END;
$$
DELIMITER ;

SET @r_result = -3;
-- 执行存储过程
CALL execute_seckill(1003, 15859584985, NOW(), @r_result);
-- 获取结果
SELECT @r_result;