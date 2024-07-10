package org.example.bingowebflux.domain.mapper.player;

import org.example.bingowebflux.domain.document.PlayerDocument;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerDTO;
import org.example.bingowebflux.domain.dto.player.PlayerResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerUpdateRequestDTO;
import org.example.bingowebflux.domain.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PlayerMapper extends EntityMapper {
  PlayerDTO toPlayer(String nickname);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  PlayerDocument toDocument(PlayerDTO player);

  @Mapping(target = "id", qualifiedByName = "toId")
  PlayerDTO toPlayer(PlayerDocument document);

  PlayerDTO toPlayer(PlayerUpdateRequestDTO request);

  PlayerResponseDTO toResponse(PlayerDTO player);

  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "content", qualifiedByName = "toContent")
  PageResponseDTO<PlayerResponseDTO> toPage(PageImpl<PlayerDTO> page);

  @Named("toContent")
  default List<PlayerResponseDTO> toContent(List<PlayerDTO> list) {
    return list.stream().map(this::toResponse).toList();
  }
}

