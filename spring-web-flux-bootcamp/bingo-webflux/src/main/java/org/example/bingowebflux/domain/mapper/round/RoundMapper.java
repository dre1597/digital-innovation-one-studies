package org.example.bingowebflux.domain.mapper.round;

import org.apache.commons.lang3.tuple.Pair;
import org.example.bingowebflux.domain.document.RoundDocument;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.round.*;
import org.example.bingowebflux.domain.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RoundMapper extends EntityMapper {
  @Mapping(target = "id", qualifiedByName = "toObjectId")
  RoundDocument toDocument(RoundDTO round);

  @Mapping(target = "id", qualifiedByName = "toId")
  RoundDTO toRound(RoundDocument document);

  RoundResponseDTO toResponse(RoundDTO round);

  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "content", qualifiedByName = "toContent")
  PageResponseDTO<RoundResponseDTO> toPage(PageImpl<RoundDTO> page);

  @Mapping(source = "pair.right", target = "roundId")
  @Mapping(source = "pair.left.owner.id", target = "owner.id")
  @Mapping(source = "pair.left.owner.nickname", target = "owner.nickname")
  @Mapping(source = "pair.left.numbers", target = "numbers")
  RoundCardOnlyResponseDTO toRoundCardResponse(Pair<RoundCardDTO, String>pair);

  RoundNumberResponseDTO toRoundNumberResponse(Integer number);

  RoundDrawResponseDTO toRoundDrawResponse(RoundDTO round);

  @Named("toContent")
  default List<RoundResponseDTO> toContent(List<RoundDTO> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
