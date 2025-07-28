--liquibase formatted sql
--changeset userassignment:create_db_userassignment.sql
--preconditions onFail:MARK_RAN onError:WARN
DROP TABLE IF EXISTS userassignment_resource_user;
CREATE TABLE userassignment_resource_user (
	id int AUTO_INCREMENT,
	id_resource INT DEFAULT 0 NOT NULL,
	resource_type VARCHAR(255) DEFAULT NULL,
	id_user INT DEFAULT NULL,
	assignment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	is_active SMALLINT DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);

CREATE INDEX userassignment_resource_user_id_resource_fk ON userassignment_resource_user(id_resource);
CREATE INDEX userassignment_resource_user_resource_type_fk ON userassignment_resource_user(resource_type);
CREATE INDEX userassignment_resource_user_id_admin_fk ON userassignment_resource_user(id_user);