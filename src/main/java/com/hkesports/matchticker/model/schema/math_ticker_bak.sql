	CREATE TABLE `function` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `name` varchar(255) NOT NULL,
	  `url` varchar(255) DEFAULT NULL,
	  `is_menu` tinyint(4) DEFAULT NULL,
	  `order_num` smallint(6) DEFAULT '0',
	  `icon_css` varchar(64) DEFAULT NULL,
	  `description` varchar(255) DEFAULT NULL,
	  `parent_function_id` bigint(20) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  KEY `function_parentFunction_FK_idx` (`parent_function_id`),
	  CONSTRAINT `function_parentFunction_FK` FOREIGN KEY (`parent_function_id`) REFERENCES `function` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
	
	CREATE TABLE `role_function` (
	  `role_id` bigint(20) NOT NULL,
	  `function_id` bigint(20) NOT NULL,
	  KEY `rf_f_FK_idx` (`function_id`),
	  KEY `rf_r_FK_idx` (`role_id`),
	  CONSTRAINT `rf_f_FK` FOREIGN KEY (`function_id`) REFERENCES `function` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT `rf_r_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	CREATE TABLE `persistent_logins` (
	  `username` varchar(64) NOT NULL,
	  `series` varchar(64) NOT NULL,
	  `token` varchar(64) NOT NULL,
	  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	  PRIMARY KEY (`series`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table ability_upgrades_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id INT(11),
        level INT(11),
        time INT(11),
        player_id BIGINT(20),
        schedule_game_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table game_picks_bans_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        game_type varchar(10),
        is_pick TINYINT,
        item_order INT(11),
        team_id BIGINT(20),
        hero_id BIGINT(20),
        schedule_game_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table league_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        color varchar(7),
        default_series_id varchar(50),
        default_tournament_id BIGINT(20),
        label varchar(50),
        league_image varchar(255),
        menu_weight INT(11),
        no_vods SMALLINT(6),
        published SMALLINT(6),
        short_name varchar(50),
        url varchar(255),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table live_streams_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        embed_code TEXT,
        language varchar(100),
        type varchar(100),
        url varchar(255),
        league_id BIGINT(20),
        schedule_game_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table player_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        bio TEXT,
        contract_expiration DATETIME,
        country varchar(2),
        facebook_url varchar(255),
        hometown varchar(100),
        is_starter SMALLINT(6),
        photo_url varchar(255),
        player_full_name varchar(128) CHARACTER SET utf8mb4 not null,
        player_icon_large varchar(10),
        player_icon_small varchar(10),
        player_name varchar(128) CHARACTER SET utf8mb4 not null,
        player_url varchar(255),
        residency varchar(255),
        twitter_url varchar(255),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table schedule_game_detail_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        baron_kills INT(11),
        barracks_status INT(11),
        dominion_victory_score INT(11),
        dragon_kills INT(11),
        first_baron TINYINT,
        first_blood TINYINT,
        first_dragon TINYINT,
        first_inhibitor TINYINT,
        first_tower TINYINT,
        inhibitor_kills INT(11),
        position varchar(20),
        tower_kills INT(11),
        tower_status INT(11),
        vilemaw_kills INT(11),
        win varchar(5),
        guess_count BIGINT(20),
        schedule_game_id BIGINT(20),
        team_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table schedule_game_player_detail_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        assists INT(11),
        champ_level INT(11),
        combat_player_score INT(11),
        deaths INT(11),
        double_kills INT(11),
        end_level INT(11),
        first_blood_assist TINYINT,
        first_blood_kill TINYINT,
        first_inhibitor_assist TINYINT,
        first_inhibitor_kill TINYINT,
        first_tower_assist TINYINT,
        first_tower_kill TINYINT,
        gold_earned INT(11),
        gold_per_min INT(11),
        gold_spent INT(11),
        hero_damage INT(11),
        hero_healing INT(11),
        inhibitor_kills INT(11),
        kda DOUBLE,
        killing_sprees INT(11),
        kills INT(11),
        lane varchar(255),
        largest_critical_strike INT(11),
        largest_killing_spree INT(11),
        largest_multi_kill INT(11),
        longest_time_spent_living INT(11),
        magic_damage_dealt INT(11),
        magic_damage_dealt_to_champions INT(11),
        magical_damage_taken INT(11),
        minions_killed INT(11),
        neutral_minions_killed INT(11),
        neutral_minions_killed_enemy_jungle INT(11),
        neutral_minions_killed_team_jungle INT(11),
        objective_player_score INT(11),
        penta_kills INT(11),
        physical_damage_dealt INT(11),
        physical_damage_dealt_to_champions INT(11),
        physical_damage_taken INT(11),
        player_slot INT(11),
        quadra_kills INT(11),
        role varchar(255),
        sight_wards_bought_in_game INT(11),
        total_damage_dealt INT(11),
        total_damage_dealt_to_champions INT(11),
        total_damage_taken INT(11),
        total_gold INT(11),
        total_heal INT(11),
        total_minions_killed INT(11),
        total_player_score INT(11),
        total_score_rank INT(11),
        total_time_crowd_control_dealt INT(11),
        total_units_healed INT(11),
        tower_damage INT(11),
        triple_kills INT(11),
        true_damage_dealt INT(11),
        true_damage_dealt_to_champions INT(11),
        true_damage_taken INT(11),
        turret_kills INT(11),
        unreal_kills INT(11),
        vision_wards_bought_in_game INT(11),
        wards_killed INT(11),
        wards_placed INT(11),
        xp_per_min INT(11),
        player_id BIGINT(20) not null,
        schedule_game_id BIGINT(20) not null,
        schedule_game_detail_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table schedule_game_player_items_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        item_id BIGINT(20),
        item_type varchar(10),
        sequence SMALLINT(6),
        player_id BIGINT(20),
        schedule_game_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table schedule_game_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        date_time DATETIME,
        first_blood_time int4,
        game_creation BIGINT(20),
        game_length int4,
        game_mode SMALLINT(6),
        game_number SMALLINT(6),
        human_players int4,
        leg_url varchar(255),
        lobby_type SMALLINT(6),
        max_games SMALLINT(6),
        negative_vote int4,
        no_vods SMALLINT(6),
        platform_game_id BIGINT(20),
        platform_id varchar(50),
        positive_votes int4,
        tournament_round INT(11),
        winner_id BIGINT(20),
        schedule_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table schedule_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        a_side_support_count BIGINT(20),
        b_side_support_count BIGINT(20),
         date_time DATETIME,
        display TINYINT,
        end_time DATETIME,
        highlight TINYINT not null,
        is_finished varchar(10),
        is_live TINYINT(4),
        match_archive_url varchar(2048),
        match_live_url varchar(2048),
        max_games SMALLINT(6),
        player_a_name varchar(64),
        player_b_name varchar(64),
        polldaddy_id varchar(255),
        results varchar(32),
        start_time DATETIME not null,
        status TINYINT(4),
        team_a_id int8,
        team_a_name varchar(50),
        team_b_id int8,
        team_b_name varchar(50),
        tourament_name varchar(255),
        winner_id SMALLINT(6),
        game_id BIGINT(20),
        tourament_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table team_member_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        is_starter SMALLINT(6),
        member_country varchar(2),
        member_full_Name varchar(128) CHARACTER SET utf8mb4,
        member_icon_large varchar(10),
        member_icon_small varchar(10),
        member_name varchar(128) CHARACTER SET utf8mb4,
        role varchar(255),
        team_url varchar(255),
        player_id BIGINT(20),
        team_id BIGINT(20),
        tourament_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table team_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        bio TEXT,
        country varchar(2),
        logo_url varchar(255),
        no_players SMALLINT(6),
        rating varchar(255),
        team_full_name varchar(128) CHARACTER SET utf8mb4,
        team_icon_large varchar(10),
        team_icon_small varchar(10),
        team_name varchar(128) CHARACTER SET utf8mb4,
        team_photo_url varchar(255),
        team_url varchar(255),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table tourament_r (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        draw_score SMALLINT(6) not null,
        is_finished SMALLINT(6),
        lose_score SMALLINT(6) not null,
        no_vods SMALLINT(6),
        published SMALLINT(6),
        season varchar(50),
        tourament_channel_url varchar(255),
        tourament_description TEXT,
        tourament_from_date DATETIME,
        tourament_icon_Huge varchar(10),
        tourament_icon_large varchar(10),
        tourament_icon_small varchar(10),
        tourament_Name varchar(255) not null,
        tourament_short_name varchar(100) not null,
        tourament_site_url varchar(255),
        tourament_to_date DATETIME,
        tournament_competition_system varchar(10),
        win_score SMALLINT(6) not null,
        winner BIGINT(20),
        league_id BIGINT(20),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table tournament_batchjob_tmp (
        id BIGINT(20) not null AUTO_INCREMENT,
        create_date DATETIME,
        update_date DATETIME,
        api_id BIGINT(20),
        game_type varchar(10),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    alter table ability_upgrades_r 
        add constraint FK_tccrrhkxx8elydlc6qx8pnje9 
        foreign key (player_id) 
        references player_r (id);

    alter table ability_upgrades_r 
        add constraint FK_tel8be8d29is97puapas01kqp 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table game_picks_bans_r 
        add constraint FK_rwkxgj59dfwkiyump6uh6lipm 
        foreign key (hero_id) 
        references hero (id);

    alter table game_picks_bans_r 
        add constraint FK_jgdow0sckpj1ko5d2yecddbfo 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table live_streams_r 
        add constraint FK_c0c1qyyh3bulod4ddyf5ptrgp 
        foreign key (league_id) 
        references league_r (id);

    alter table live_streams_r 
        add constraint FK_naii8eecl0kbgftc1vful6c6o 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table schedule_game_detail_r 
        add constraint FK_cavaj77ujblbihip1xarmaysv 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table schedule_game_detail_r 
        add constraint FK_o934rqbmhtl7f7nwxsdufypj7 
        foreign key (team_id) 
        references team_r (id);

    alter table schedule_game_player_detail_r 
        add constraint FK_8n9hdlgl177sq0w2na7o0xnv6 
        foreign key (player_id) 
        references player_r (id);

    alter table schedule_game_player_detail_r 
        add constraint FK_eu3wp466eul0geqgaqcub04pl 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table schedule_game_player_detail_r 
        add constraint FK_5ww2g6d742xvm9hmfk6nikp8v 
        foreign key (schedule_game_detail_id) 
        references schedule_game_detail_r (id);

    alter table schedule_game_player_items_r 
        add constraint FK_lpm2u69kikdp5cvoicscijie7 
        foreign key (player_id) 
        references player_r (id);

    alter table schedule_game_player_items_r 
        add constraint FK_f5yuo48cvqagdf3r07d7a7rgc 
        foreign key (schedule_game_id) 
        references schedule_game_r (id);

    alter table schedule_game_r 
        add constraint FK_nvjbmis48a6sw4f911e9sj9j2 
        foreign key (schedule_id) 
        references schedule_r (id);

    alter table schedule_r 
        add constraint FK_ot5imufuym15wrktfjmgi6g9f 
        foreign key (game_id) 
        references game (id);

    alter table schedule_r 
        add constraint FK_gfgmla40fo4ik9pkeb6b6t4jj 
        foreign key (tourament_id) 
        references tourament_r (id);
        
    alter table team_member_r 
        add constraint FK_stw89xn5fsgwr4xvndlkk3pme 
        foreign key (player_id) 
        references player_r (id);

    alter table team_member_r 
        add constraint FK_gkym1qd87iduyupng738dwkw1 
        foreign key (team_id) 
        references team_r (id);

    alter table tourament_r 
        add constraint FK_q7dgepusfdasig9nfnh9928fk 
        foreign key (league_id) 
        references league_r (id);
