
INSERT INTO `configuracion` (`url`,`endpoint_code`,`cif`, `cp`, `direccion`, `email`, `nombre`, `poblacion`, `provincia`, `smtp_configuracion`, `telefono`, `www`) VALUES
	('https://demo.bolder.com','00020001','A36651313', '15008', 'C/ Gambrinus 11', 'info@sweeepend.com', 'Vego supermercados s.a.', 'La Coruña', 'La Coruña', '{"mailSender":"info@bolder.com","host":"webmail.bolder.com","port":25,"username":"info@bolder.com","password":"112233kk","smtpAuth":true,"smtpTtls":false}', '981179305', 'www.vegalsa.es');

INSERT INTO `usuario` (`apellido`, `email`, `enabled`, `nombre`, `password`, `role`, `username`) VALUES
	('admin', 'admin', b'1', 'admin', '$2a$10$y3p2i2A0NJ9.0Stlq6w7IuEF4m34T3IOwGV4aL24f4iINfsDzjuR.', 1, 'admin')


