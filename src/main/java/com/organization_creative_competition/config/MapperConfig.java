package com.organization_creative_competition.config;

import com.organization_creative_competition.dto.CompetitionDto;
import com.organization_creative_competition.dto.ParticipantDto;
import com.organization_creative_competition.dto.UserDto;
import com.organization_creative_competition.model.Competition;
import com.organization_creative_competition.model.Participant;
import com.organization_creative_competition.model.User;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Competition.class, CompetitionDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(User.class, UserDto.class)
                .field("userInfo.surname", "surname")
                .field("userInfo.name", "name")
                .field("userInfo.patronymic", "patronymic")
                .field("userInfo.description", "description")
                .field("userInfo.dateOfBirth", "dateOfBirth")
                .byDefault()
                .register();

        mapperFactory.classMap(Participant.class, ParticipantDto.class)
                .byDefault()
                .register();
    }
}
