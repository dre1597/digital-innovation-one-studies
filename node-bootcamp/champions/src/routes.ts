import { Router } from 'express';
import * as playController from './controllers/player.controller';
import * as clubController from './controllers/club.controller';

const router = Router();

router.get('/players', playController.getPlayer);
router.post('/players', playController.postPlayer);
router.delete('/players/:id', playController.deletePlayer);
router.patch('/players/:id', playController.updatePlayer);
router.get('/players/:id', playController.getPlayerById);

router.get('/clubs', clubController.getClubs);

export default router;
