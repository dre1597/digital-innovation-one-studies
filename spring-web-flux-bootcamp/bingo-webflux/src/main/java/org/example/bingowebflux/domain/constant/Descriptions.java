package org.example.bingowebflux.domain.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Descriptions {
  public static final String PAGE_DESCRIPTION = "Define the page number of the query";
  public static final String SIZE_DESCRIPTION = "Define the size of the query";

  public static final String PAGE_RESPONSE_CONTENT = "Content of the page";
  public static final String PAGE_RESPONSE_LAST = "Indicates if is the last page";
  public static final String PAGE_RESPONSE_TOTAL_PAGES = "Indicates the total number of pages";
  public static final String PAGE_RESPONSE_TOTAL_ELEMENTS = "Indicates the total number of elements";
  public static final String PAGE_RESPONSE_HAS_NEXT = "Indicates if there is a next page";
  public static final String PAGE_RESPONSE_NUMBER = "Indicates the current page number";
  public static final String PAGE_RESPONSE_SIZE = "Indicates the size of the page";

  public static final String EXCEPTION_RESPONSE_TIMESTAMP = "Timestamp of the exception";
  public static final String EXCEPTION_RESPONSE_ERROR_DESCRIPTION = "Description of the error";
  public static final String EXCEPTION_RESPONSE_LIST_FIELD = "List of descriptions of the fields that have an error";
  public static final String EXCEPTION_RESPONSE_HTTP_STATUS = "Http status of the exception";
  public static final String EXCEPTION_FIELD_RESPONSE_NAME = "Name of the field that has an error";
  public static final String EXCEPTION_FIELD_RESPONSE_MESSAGE = "Message of the field that has an error";

  public static final String PLAYER_CONTROLLER = "Player Controller";
  public static final String PLAYER_CONTROLLER_DESCRIPTION = "Controller for player operations";
  public static final String PLAYER_FIELD_ID_DESCRIPTION = "Param to identify the player";
  public static final String PLAYER_FIELD_NICKNAME_DESCRIPTION = "Param to identify the nickname of the player";
  public static final String PLAYER_GET_LIST_SUMMARY = "Endpoint to list all players";
  public static final String PLAYER_GET_LIST_DESCRIPTION = "Get all players paginated";
  public static final String PLAYER_GET_BY_ID_SUMMARY = "Endpoint to player by id";
  public static final String PLAYER_GET_BY_ID_DESCRIPTION = "Get player by id";
  public static final String PLAYER_POST_SUMMARY = "Endpoint to create player";
  public static final String PLAYER_POST_DESCRIPTION = "Create a new player";
  public static final String PLAYER_PUT_SUMMARY = "Endpoint to update player";
  public static final String PLAYER_PUT_DESCRIPTION = "Update a player";
  public static final String PLAYER_DELETE_SUMMARY = "Endpoint to delete player";
  public static final String PLAYER_DELETE_DESCRIPTION = "Delete a player";
  public static final String PLAYER_FIELD_ID = "Identifier of the player";
  public static final String PLAYER_FIELD_NICKNAME = "Name of the player";

  public static final String ROUND_CONTROLLER = "Round Controller";
  public static final String ROUND_CONTROLLER_DESCRIPTION = "Controller for round operations";
  public static final String ROUND_GET_LIST_SUMMARY = "Endpoint to list all rounds";
  public static final String ROUND_GET_LIST_DESCRIPTION = "Get all rounds";
  public static final String ROUND_GET_BY_ID_SUMMARY = "Endpoint to get round by id";
  public static final String ROUND_GET_BY_ID_DESCRIPTION = "Get round by id";
  public static final String ROUND_GET_LAST_NUMBER_SUMMARY = "Endpoint to get last number";
  public static final String ROUND_GET_LAST_NUMBER_DESCRIPTION = "Get last number";
  public static final String ROUND_CREATE_SUMMARY = "Endpoint to create round";
  public static final String ROUND_CREATE_DESCRIPTION = "Create a new round";
  public static final String ROUND_GENERATE_NUMBER_SUMMARY = "Endpoint to generate number";
  public static final String ROUND_GENERATE_NUMBER_DESCRIPTION = "Generate a new number";
  public static final String ROUND_GENERATE_CARD_SUMMARY = "Endpoint to generate card";
  public static final String ROUND_GENERATE_CARD_DESCRIPTION = "Generate a new card";
  public static final String ROUND_STATUS_DESCRIPTION = "Status of the round";
  public static final String ROUND_ID_DESCRIPTION = "Identifier of the round";
  public static final String ROUND_DRAWN_NUMBERS = "Drawn Numbers";
  public static final String ROUND_WINNERS = "Winners";
  public static final String ROUND_CARDS = "Cards";
  public static final String ROUND_LAST_NUMBER = "Last Number";
  public static final String ROUND_QTD_DRAWN_NUMBERS = "Qtd of drawn numbers";
  public static final String ROUND_CARD_NUMBERS = "Card Numbers";
  public static final String ROUND_CARD_OWNER = "Card Owner";
  public static final String ROUND_PLAYER_NICKNAME = "Player Nickname";
  public static final String ROUND_PLAYER_ID = "Player Identification";
}
