INSERT INTO users (first_name, last_name, email, phone)
VALUES ('Dan', 'BoshDan', 'info@BoshDan.com', '213-666-4444'), 
		('Carol', 'Butters', 'sam@d1tm.org', '767-888-2323'), 
		('David', 'Biten', 'biten@district112.org', '213-666-3333'), 
		('Lisa', '.Twlight', 'sales@district113.org', '313-555-4444'), 
		('Randall', 'Bosher', 'info@BosherRandall.com', '313-555-4224'), 
		('Susan', 'Zutters', 'susan@d4tm.org', '713-222-6227'), 
		('Faid', 'Riddenzer', 'members@district142.org', '717-212-9227'), 
		('Cellesa', 'Dawne', 'recruitment@district163.org', '913-222-6233'), 
		('Jennifer', 'Daniels', 'hr@JenDaniels.com', '709-444-6228'), 
		('Sally', 'Walkers', 'sally@d133tm.org', '413-282-6887'), 
		('Dan', 'Givens', 'givens@district110.org', '703-202-6999'), 
		('Reese', 'Otter', 'otterR@district17.org', '718-522-6427'),
		('Wiggy', 'Roschild', 'RoschileR@dwp-1745.org', '213-822-7777'),
		('Candie', 'Oswald', 'Candie@Oswald.org', '818-674-2764'),
        ('Callie', 'Salkers', 'callie@d553tm.org', '313-882-6889'),
        ('Sally', 'Walkers', 'sally@d133tm.org', '403-232-0880')
		
;
INSERT INTO user_goals (goals_pk, user_fk, calorie_limit, daily_calorie_total, carbs_grams_limit)
VALUES (1, 1, 1200, 1575, 435),
	(2 ,2, 1300, 1100, 300),
	(3, 3, 1400, 1475, 435),
	(4, 4, 1450, 1200, 435),
	(5, 5, 1525, 1585, 435),
	(6, 6, 1575, 1400, 300),
	(7, 7, 1675, 1475, 540),
	(8, 8, 1770, 1700, 570),
	(9, 9, 1800, 1825, 435),
	(10, 10, 1850, 1800, 925),
	(11, 11, 1925, 1875, 962),
	(12, 12, 1985, 2900, 992),
    (15, 16, 3300, 1004, 578),
    (16, 16, 2200, 1544, 556)
;
-- select * from users;
-- select * from user_goals;
-- Clean up
-- can't drop this table it's ref in user_goals
DROP TABLE users;