package org.example.reactivebingo.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactivebingo.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PlayerService {
  private final PlayerRepository playerRepository;
}
