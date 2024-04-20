import { Player } from '../models/player.model';
import { Statistics } from '../models/statistics.model';

const database: Player[] = [
  {
    id: 1,
    name: 'Lionel Messi',
    club: 'Paris Saint-Germain',
    nationality: 'Argentina',
    position: 'Forward',
    statistics: {
      overall: 93,
      pace: 85,
      shooting: 94,
      passing: 91,
      dribbling: 95,
      defending: 38,
      physical: 65,
    },
  },
  {
    id: 2,
    name: 'Cristiano Ronaldo',
    club: 'Manchester United',
    nationality: 'Portugal',
    position: 'Forward',
    statistics: {
      overall: 92,
      pace: 89,
      shooting: 93,
      passing: 82,
      dribbling: 88,
      defending: 35,
      physical: 78,
    },
  },
  {
    id: 3,
    name: 'Robert Lewandowski',
    club: 'Bayern Munich',
    nationality: 'Poland',
    position: 'Forward',
    statistics: {
      overall: 91,
      pace: 80,
      shooting: 92,
      passing: 78,
      dribbling: 83,
      defending: 40,
      physical: 84,
    },
  },
  {
    id: 4,
    name: 'Kevin De Bruyne',
    club: 'Manchester City',
    nationality: 'Belgium',
    position: 'Midfielder',
    statistics: {
      overall: 91,
      pace: 76,
      shooting: 85,
      passing: 92,
      dribbling: 87,
      defending: 62,
      physical: 79,
    },
  },
  {
    id: 5,
    name: 'Kylian Mbappé',
    club: 'Paris Saint-Germain',
    nationality: 'France',
    position: 'Forward',
    statistics: {
      overall: 90,
      pace: 96,
      shooting: 86,
      passing: 79,
      dribbling: 91,
      defending: 39,
      physical: 76,
    },
  },
  {
    id: 6,
    name: 'Lionel Messi',
    club: 'Paris Saint-Germain',
    nationality: 'Argentina',
    position: 'Forward',
    statistics: {
      overall: 93,
      pace: 85,
      shooting: 94,
      passing: 91,
      dribbling: 95,
      defending: 38,
      physical: 65,
    },
  },
  {
    id: 7,
    name: 'Cristiano Ronaldo',
    club: 'Manchester United',
    nationality: 'Portugal',
    position: 'Forward',
    statistics: {
      overall: 92,
      pace: 89,
      shooting: 93,
      passing: 82,
      dribbling: 88,
      defending: 35,
      physical: 78,
    },
  },
  {
    id: 8,
    name: 'Robert Lewandowski',
    club: 'Bayern Munich',
    nationality: 'Poland',
    position: 'Forward',
    statistics: {
      overall: 91,
      pace: 80,
      shooting: 92,
      passing: 78,
      dribbling: 83,
      defending: 40,
      physical: 84,
    },
  },
  {
    id: 9,
    name: 'Erling Haaland',
    club: 'Borussia Dortmund',
    nationality: 'Norway',
    position: 'Forward',
    statistics: {
      overall: 89,
      pace: 88,
      shooting: 90,
      passing: 75,
      dribbling: 81,
      defending: 32,
      physical: 89,
    },
  },
  {
    id: 10,
    name: 'Neymar Jr.',
    club: 'Paris Saint-Germain',
    nationality: 'Brazil',
    position: 'Forward',
    statistics: {
      overall: 91,
      pace: 92,
      shooting: 84,
      passing: 88,
      dribbling: 95,
      defending: 38,
      physical: 72,
    },
  },
  {
    id: 11,
    name: 'Mohamed Salah',
    club: 'Liverpool',
    nationality: 'Egypt',
    position: 'Forward',
    statistics: {
      overall: 90,
      pace: 94,
      shooting: 87,
      passing: 81,
      dribbling: 89,
      defending: 45,
      physical: 75,
    },
  },
  {
    id: 12,
    name: 'Virgil van Dijk',
    club: 'Liverpool',
    nationality: 'Netherlands',
    position: 'Defender',
    statistics: {
      overall: 89,
      pace: 77,
      shooting: 60,
      passing: 78,
      dribbling: 70,
      defending: 90,
      physical: 92,
    },
  },
  {
    id: 13,
    name: 'Sadio Mané',
    club: 'Liverpool',
    nationality: 'Senegal',
    position: 'Forward',
    statistics: {
      overall: 88,
      pace: 95,
      shooting: 85,
      passing: 78,
      dribbling: 90,
      defending: 45,
      physical: 78,
    },
  },
  {
    id: 14,
    name: 'Trent Alexander-Arnold',
    club: 'Liverpool',
    nationality: 'England',
    position: 'Defender',
    statistics: {
      overall: 87,
      pace: 84,
      shooting: 70,
      passing: 86,
      dribbling: 82,
      defending: 85,
      physical: 77,
    },
  },
  {
    id: 15,
    name: 'Alisson Becker',
    club: 'Liverpool',
    nationality: 'Brazil',
    position: 'Goalkeeper',
    statistics: {
      overall: 89,
      pace: 84,
      shooting: 85,
      passing: 86,
      dribbling: 48,
      defending: 90,
      physical: 77,
    },
  },
  {
    id: 16,
    name: 'Frenkie de Jong',
    club: 'Barcelona',
    nationality: 'Netherlands',
    position: 'Midfielder',
    statistics: {
      overall: 88,
      pace: 78,
      shooting: 76,
      passing: 89,
      dribbling: 88,
      defending: 80,
      physical: 82,
    },
  },
  {
    id: 17,
    name: 'Raheem Sterling',
    club: 'Manchester City',
    nationality: 'England',
    position: 'Forward',
    statistics: {
      overall: 87,
      pace: 93,
      shooting: 82,
      passing: 79,
      dribbling: 90,
      defending: 40,
      physical: 77,
    },
  },
  {
    id: 18,
    name: 'Zlatan Ibrahimović',
    club: 'AC Milan',
    nationality: 'Sweden',
    position: 'Forward',
    statistics: {
      overall: 89,
      pace: 81,
      shooting: 92,
      passing: 85,
      dribbling: 86,
      defending: 40,
      physical: 88,
    },
  },
  {
    id: 19,
    name: 'David Beckham',
    club: 'Retired',
    nationality: 'England',
    position: 'Midfielder',
    statistics: {
      overall: 85,
      pace: 76,
      shooting: 82,
      passing: 90,
      dribbling: 84,
      defending: 70,
      physical: 72,
    },
  },
];

export const findAllPlayers = async (): Promise<Player[]> => {
  return database;
};

export const findPlayerById = async (
  id: number
): Promise<Player | undefined> => {
  return database.find((player) => player.id === id);
};

export const insertPlayer = async (player: Player) => {
  database.push(player);
};

export const deleteOnePlayer = async (id: number) => {
  const index = database.findIndex((p) => p.id === id);

  if (index !== -1) {
    database.splice(index, 1);
    return true;
  }

  return false;
};

export const findAndModifyPlayer = async (
  id: number,
  statistics: Statistics
): Promise<Player> => {
  const playerIndex = database.findIndex((player) => player.id === id);

  if (playerIndex !== -1) {
    database[playerIndex].statistics = statistics;
  }

  return database[playerIndex];
};
