CREATE TABLE MEMBER (
    member_id bigserial NOT NULL,
    id_number varchar(13) NOT NULL,
    name varchar(20) NOT NULL,
    surname varchar(20) NOT NULL,
    password varchar(25) NOT NULL,
    miles int NOT NULL,
    PRIMARY KEY (member_id)
)

CREATE TABLE GOAL (
    goal_id bigserial NOT NULL,
    member_id bigint NOT NULL,
    goal_type varchar(10) NOT NULL,
    points_necessary int NOT NULL,
    points_earned int NOT NULL,
    goal_accomplished boolean NOT NULL,
    start_date date NOT NULL,
    PRIMARY KEY (goal_id),
    FOREIGN KEY (member_id) REFERENCES(MEMBER.member_id)

)

CREATE TABLE REWARD_PARTNER(
    reward_partner_id bigserial NOT NULL,
    company_name varchar(30) NOT NULL,
    admin_password varchar(20) NOT NULL,
    PRIMARY KEY (reward_partner_id)
)

CREATE TABLE REWARD (
    reward_id bigserial NOT NULL,
    reward_type varchar(20) NOT NULL,
    reward_description text,
    reward_partner bigint NOT NULL,
    mile_cost int NOT NULL,
    PRIMARY KEY (reward_id),
    FOREIGN KEY (reward_partner_id) REFERENCES(REWARD_PARTNER.reward_partner_id)
)

CREATE TABLE SUBSCRIPTION_REWARD (
    reward_id bigint NOT NULL,
    months_subcription int NOT NULL,
    FOREIGN KEY (reward_id) REFERENCES(REWARD.reward_id)
)

CREATE TABLE SUBSCRIPTION_REWARD (
    reward_id bigint NOT NULL,
    months_subcription int NOT NULL,
    FOREIGN KEY (reward_id) REFERENCES(REWARD.reward_id)
)

CREATE TABLE VOUCHER_REWARD (
    reward_id bigint NOT NULL,
    monetary_value double NOT NULL,
    FOREIGN KEY (reward_id) REFERENCES(REWARD.reward_id)
)

CREATE TABLE REWARD_MEMBER (
    reward_id bigint NOT NULL,
    member_id bigint NOT NULL,
    FOREIGN KEY (reward_id) REFERENCES(REWARD.reward_id),
    FOREIGN KEY (member_id) REFERENCES(MEMBER.member_id)
)

CREATE TABLE GAME_TILE (
    tile_id bigserial NOT NULL,
    member_id bigint NOT NULL,
    row_number int NOT NULL,
    column_number int NOT NULL,
    revealed boolean NOT NULL,
    miles_value int NOT NULL,
    PRIMARY KEY (tile_id),
    FOREIGN KEY (member_id) REFERENCES(MEMBER.member_id)
)