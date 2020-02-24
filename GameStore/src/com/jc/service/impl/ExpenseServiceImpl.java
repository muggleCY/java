package com.jc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.ExpenseDao;
import com.jc.dao.GameDao;
import com.jc.dao.UserDao;
import com.jc.exception.DownloadErrorException;
import com.jc.exception.GameStatusException;
import com.jc.exception.UserCloseException;
import com.jc.exception.UserMoneyNotEnoughException;
import com.jc.pojo.Expense;
import com.jc.pojo.Game;
import com.jc.pojo.User;
import com.jc.service.ExpenseService;
import com.jc.vo.ExpenseVo;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GameDao gameDao;
	
	@Override
	@Transactional
	public void addExpense(Integer userId,Integer gameId,Integer payWay) throws Exception {
		User userI = new User();
		userI.setId(userId);
		User user = userDao.selectUser(userI);
		if(user.getStatus() == 2){
			throw new UserCloseException(Constants.USER_CLOSED_ERROR);
		}
		Game game = gameDao.selectGame(gameId);
		if (game.getGameStatus() == 2) {
//			判断状态是否为商用
			throw new GameStatusException(Constants.GAME_IS_NOT_ONLINE);
		}
		Expense expense = new Expense();
		if (payWay == 1) {
			//话费
			if (user.getTariffe() - game.getGameTariffe() < 0) {
//				您的话费余额不足，请使用游币尝试或充值话费
				throw new UserMoneyNotEnoughException(Constants.TARIFFE_NOT_ENOUGH);
			}else{
				user.setTariffe(user.getTariffe()-game.getGameTariffe());
				expense.setMonetary(game.getGameTariffe());
			}
		}else if(payWay == 2){
			//游币
			if (user.getCurrency() - game.getGameCurrency()<0) {
//				您的游币余额不足，请使用话费尝试或者充值游币
				throw new UserMoneyNotEnoughException(Constants.CURRENCY_NOT_ENOUGH);
			}else{
				user.setCurrency(user.getCurrency() - game.getGameCurrency());
				expense.setMonetary(game.getGameCurrency());
			}
		}
		expense.setUserId(userId);
		expense.setGameId(gameId);
		expense.setOperations(payWay);
		expense.setCreateTime(new Date());
		expense.setDownloads(0);
		expenseDao.insertExpense(expense);
		userDao.updateUser(user);
	}
	@Override
	public List<ExpenseVo> showExpenseVo(Integer userId) {
		return expenseDao.selectExpenseVo(userId);
	}

	@Override
	public List<Expense> selectExpenses() {
		return expenseDao.selectExpenses();
	}
	@Override
	public Expense selectExpenseByUidAndGid(Integer userId,Integer gameId) {
		Expense expense = new Expense();
		expense.setUserId(userId);
		expense.setGameId(gameId);
		return expenseDao.selectExpenseByUidAndGid(expense);
	}
	@Override
	public void modifyExpense(Expense expense) throws Exception {
		Game game = gameDao.selectGame(expense.getGameId());
		User userI = new User();
		userI.setId(expense.getUserId());
		User user = userDao.selectUser(userI);
		if(user.getStatus() == 2){
			throw new UserCloseException(Constants.USER_CLOSED_ERROR);
		}
		if (game.getGameStatus() == 2) {
//			判断状态是否为商用
			throw new GameStatusException(Constants.GAME_IS_NOT_ONLINE);
		}
		if(expense.getDownloads() > 3){
			throw new DownloadErrorException(Constants.DOWNLOAD_OUT);
		}
		expense.setDownloads(expense.getDownloads()+1);
		expenseDao.updateExpense(expense);
	}


}
