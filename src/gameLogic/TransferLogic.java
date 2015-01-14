package gameLogic;
import libraryClasses.*;
import java.util.*;
/** This class contains all the logic for transfers
 * 
 * @author Menno
 *
 */
public abstract class TransferLogic {

	/** This method returns the answer for a transfer request for a player that you have already bid for before
	 * 
	 * @param player The player you want to buy
	 * @param bid The bid you place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers the list with transfers that already exist
	 * @param returnedprice the price that was returned last time you tried to buy this player
	 * @return true if the team has accepted your offer, false if the team has denied your offer
	 */
	public static boolean getAnswerForExistingTransfer(Player player, double bid, Library library, TransferList existingTransfers, double returnedprice) {
		if (bid<returnedprice) {
			return false;
		} else {
			return TransferLogic.getAnswer(player, bid, library, existingTransfers);
		}
	}
	/** This method returns the answer for a transfer request
	 * 
	 * @param player The player you want to buy
	 * @param bid The bid you place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers The list with transfers that already exist
	 * @return true if the team has accepted your offer, false if the team has denied your offer
	 */
	public static boolean getAnswer(Player player, double bid, Library library, TransferList existingTransfers) {
		Team team = library.getTeamForName(player.getTeam());
		ArrayList<Player> playerlist = new ArrayList<Player>();
		for (int i=0;i<team.getTeam().size();i++) {
			if (player.getPlayerType().equals(team.getTeam().get(i).getPlayerType())) {
				playerlist.add(team.getTeam().get(i));
			}
		}
		int[] ratings = new int[playerlist.size()];
		if(player.getPlayerType().equals("Attacker")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]= (int) (0.6*p.getFinishingValue() + 0.2*p.getDribblingValue() + 0.2*p.getStaminaValue());
			}
		} if (player.getPlayerType().equals("Midfielder")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.15*p.getFinishingValue() + 0.4*p.getDribblingValue() + 0.3*p.getStaminaValue() + 0.15*p.getDefenseValue());
				
			}
		} if (player.getPlayerType().equals("Defender")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.05*p.getDribblingValue() + 0.25*p.getStaminaValue() + 0.7*p.getDefenseValue());
				
			}
		} if (player.getPlayerType().equals("Goalkeeper")) {
			for (int i=0;i<playerlist.size();i++) {
				Goalkeeper p = (Goalkeeper)playerlist.get(i);
				ratings[i]=p.getGoalkeeperValue();
			}
		}
		
		if (playerlist.size()==1) {
			
			return false;
		}
		
		int playerid=playerlist.indexOf(player);
		int typecounter=1;
		for (int i=0;i<ratings.length;i++) {
			if (i==playerid) {
				
			} else {
				if (ratings[playerid]<ratings[i]) {
					typecounter++;
				}
			}
		}
		int random = GameLogic.randomGenerator(1, 100);
		double percentage = bid/player.getPrice().doubleValue()*100-100;
		if (typecounter==1||typecounter==2) {
			if(percentage<0) {
				return false;
				
			} if (percentage>=0 && percentage<5) {
				if (random<=10) {
					return true;
				} else return false;
				
				
			} if (percentage>=5 && percentage<10) {
				if (random<=20) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=30) {
					return true;
				} else return false;
			} if (percentage>=15 && percentage<20) {
				if (random<=40) {
					return true;
				} else return false;
			} if (percentage>=20 && percentage<25) {
				if (random<=50) {
					return true;
				} else return false;
			} if (percentage>=25 && percentage<30) {
				if (random<=75) {
					return true;
				} else return false;
			} else return true;
		} if (typecounter==3 || typecounter==4 || typecounter==5) {
			if (percentage<-10) {
				return false;
			} if (percentage>=-10 && percentage<0) {
				if (random<=5) {
					return true;
				} else return false;
			} if (percentage>=0 && percentage <5) {
				if (random<=15) {
					return true;
				} else return false;
			} if (percentage>=5 && percentage<10) {
				if (random<=30) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=45) {
					return true;
				} else return false;
			} if (percentage>=15 && percentage<20) {
				if (random<=60) {
					return true;
				} else return false;
			} if (percentage>=20 && percentage<25) {
				if (random<=80) {
					return true;
				} else return false;
			} else return true;
		} else {
			if (percentage<-10) {
				return false;
			} if (percentage>=-10 && percentage<0) {
				if (random<=20) {
					return true;
				} else return false;
			} if (percentage>=0 && percentage<5) {
				if (random<=45) {
					return true;
				} else return false;
			} if (percentage>=5 && percentage<10) {
				if (random<=70) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=85) {
					return true;
				} else return false;
			} else return true;
		}
		
		
	}
	/** Calling this method represents requesting a transfer
	 * 	
	 * @param player The player you want to buy
	 * @param playersTeam The team you are currently managing
	 * @param bid The bid you want to place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers The list with transfers that already exist
	 * @return a String with a meaningful message (to show to the user), about the acceptance or denial of his/her bid for that player
	 */
	public static String requestTransfer(Player player, Team playersTeam, double bid, Library library, TransferList existingTransfers) {
		if (existingTransfers.getTransfer(player)==null) {
			boolean answer = TransferLogic.getAnswer(player, bid, library, existingTransfers);
			if (answer) {
				Team opponentsTeam = library.getTeamForName(player.getTeam());
				if (opponentsTeam.getPositions().contains(player)) {
					
					boolean itsdone=false;
					for (int i=0;i<opponentsTeam.getTeam().size();i++) {
						if (!itsdone) {
							Player p = opponentsTeam.getTeam().get(i);
							if (p.getPlayerType().equals(player.getPlayerType()) && !(opponentsTeam.getPositions().contains(p))) {
								opponentsTeam.replacePlayerInCurrentTeam(player, p);
								itsdone=true;
							}
						}
					}
					if (!itsdone) {
						for (int i=0;i<opponentsTeam.getTeam().size();i++) {
							if (!itsdone) {
								Player p = opponentsTeam.getTeam().get(i);
								if (((p instanceof FieldPlayer && player instanceof FieldPlayer) || (p instanceof Goalkeeper && player instanceof Goalkeeper)) && !(opponentsTeam.getPositions().contains(p))) {
									opponentsTeam.replacePlayerInCurrentTeam(player, p);
									itsdone=true;
								}
							}
						}
					}
					
					
				}
				
				playersTeam.add(player);
				opponentsTeam.getTeam().remove(player);
				player.setTeam(playersTeam.getTeamName());
				playersTeam.setBudget(playersTeam.getBudget()-bid);
				opponentsTeam.setBudget(opponentsTeam.getBudget()+bid);
				return "Congratulations! Your bid of " + bid + " got accepted and " + player.getName() + " is now part of your team";
			} else {
				double percentage = bid/player.getPrice().doubleValue()*100-100;
				double returnedprice=0;
				double price = player.getPrice().doubleValue();
				if (percentage<-10) {
					returnedprice=0.9*price;
				} if (percentage>=-10 && percentage<0) {
					returnedprice=price;
				} if (percentage>=0 && percentage<5) {
					returnedprice=1.05*price;
				} if (percentage>=5 && percentage<10) {
					returnedprice=1.1*price;
				} if (percentage>=10 && percentage<15) {
					returnedprice=1.15*price;
				} if (percentage>=15 && percentage<20) {
					returnedprice=1.2*price;
				} if (percentage>=20 && percentage<25) {
					returnedprice=1.25*price;
				} if (percentage>=25 && percentage<30) {
					returnedprice=1.3*price;
				} 
				
				TransferInProgress tp = new TransferInProgress(player, returnedprice, bid);
				existingTransfers.addTransfer(tp);
				
				return player.getTeam() + " did not accept your offer of " + bid + " for " + player.getName() + ". They have indicated they want at least " + returnedprice + " for this player";
				
			}
		} else {
			TransferInProgress tp = existingTransfers.getTransfer(player);
			boolean answer = TransferLogic.getAnswerForExistingTransfer(player, bid, library, existingTransfers, tp.getPriceReturned());
			if (answer) {
				Team opponentsTeam = library.getTeamForName(player.getTeam());
				if (opponentsTeam.getPositions().contains(player)) {
					boolean itsdone=false;
					for (int i=0;i<opponentsTeam.getTeam().size();i++) {
						if (!itsdone) {
							Player p = opponentsTeam.getTeam().get(i);
							if (p.getPlayerType().equals(player.getPlayerType()) && !(opponentsTeam.getPositions().contains(p))) {
								opponentsTeam.replacePlayerInCurrentTeam(player, p);
								itsdone=true;
							}
						}
					}
					if (!itsdone) {
						for (int i=0;i<opponentsTeam.getTeam().size();i++) {
							if (!itsdone) {
								Player p = opponentsTeam.getTeam().get(i);
								if (((p instanceof FieldPlayer && player instanceof FieldPlayer) || (p instanceof Goalkeeper && player instanceof Goalkeeper)) && !(opponentsTeam.getPositions().contains(p))) {
									opponentsTeam.replacePlayerInCurrentTeam(player, p);
									itsdone=true;
								}
							}
						}
					}
					
					
				}
				
				playersTeam.add(player);
				opponentsTeam.getTeam().remove(player);
				player.setTeam(playersTeam.getTeamName());
				existingTransfers.getTransfers().remove(tp);
				return "Congratulations! Your bid of " + bid + " got accepted and " + player.getName() + " is now part of your team";
				
			} else {
				double pricereturned = existingTransfers.getTransfer(player).getPriceReturned();
				
				if (bid>pricereturned) {
					double percentage = bid/player.getPrice().doubleValue()*100-100;
					double returnedprice=0;
					double price = player.getPrice().doubleValue();
					if (percentage<-10) {
						returnedprice=0.9*price;
					} if (percentage>=-10 && percentage<0) {
						returnedprice=price;
					} if (percentage>=0 && percentage<5) {
						returnedprice=1.05*price;
					} if (percentage>=5 && percentage<10) {
						returnedprice=1.1*price;
					} if (percentage>=10 && percentage<15) {
						returnedprice=1.15*price;
					} if (percentage>=15 && percentage<20) {
						returnedprice=1.2*price;
					} if (percentage>=20 && percentage<25) {
						returnedprice=1.25*price;
					} if (percentage>=25 && percentage<30) {
						returnedprice=1.3*price;
					}
					existingTransfers.getTransfer(player).setPriceReturned(returnedprice);
				}
				
				existingTransfers.getTransfer(player).setBid(bid);
				return player.getTeam() + " did not accept your offer of " + bid + " for " + player.getName() + ". They have indicated they want at least " + existingTransfers.getTransfer(player).getPriceReturned() + " for this player";
			}
			
		}
		
	}
	/** With this method you can request selling a player from your team
	 * 	
	 * @param player the player you want to sell
	 * @param playersTeam the team you are currently managing
	 * @param askingPrice the price you ask for the player
	 * @param library the library containing all the teams and players
	 * @return a String with information on succession/failure of selling the player
	 */
	public static String requestSell(Player player, Team playersTeam, double askingPrice, Library library) {
		if (!player.isCanBeSold()) {
			return "You can't try to sell this player until after " + player.getDaysNotForSale() + " more rounds";
		}
		
		ArrayList<Team> teamswithbudget = new ArrayList<Team>();
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			if (t!=playersTeam) {
				if (t.getBudget()>=askingPrice) {
					teamswithbudget.add(t);
				}
			}
			
		}
		if (teamswithbudget.size()==0) {
			player.triedToSell();
			return "Your player was not bought by any team, due to the fact that none of the teams has got enough money to fulfill your asking price";
		}
		
		double percentage = askingPrice/player.getPrice().doubleValue()*100-100;
		boolean sell = false;
		int random = GameLogic.randomGenerator(1, 100);
		if (percentage<-10) {
			sell=true;
		} if (percentage >=-10 && percentage<0) {
			if (random<=95) {
				sell=true;
			}
		} if (percentage>=0 && percentage <5) {
			if (random<=75) {
				sell=true;
			}
		} if (percentage>=5 && percentage <10) {
			if (random<=50) {
				sell=true;
			}
		} if (percentage>=10 && percentage <15) {
			if (random<=30) {
				sell=true;
			}
		} if (percentage>=15 && percentage <20) {
			if (random<=20) {
				sell=true;
			}
		} if (percentage>=20 && percentage<25) {
			if (random<=10) {
				sell=true;
			}
		} if (percentage>=25 && percentage<30) {
			if (random==1) {
				sell=true;
			}
		}
		
		if (sell) {
			Team buyingTeam = teamswithbudget.get(GameLogic.randomGenerator(0, teamswithbudget.size()-1));
			playersTeam.setBudget(playersTeam.getBudget()+askingPrice);
			buyingTeam.setBudget(buyingTeam.getBudget()-askingPrice);
			buyingTeam.add(player);
			player.setTeam(buyingTeam.getTeamName());
			playersTeam.getTeam().remove(player);
			player.setNumber(buyingTeam.getTeam().size());
			
			
			return "Congratulations! " + player.getName() + " got bought by " + buyingTeam.getTeamName() + " for the price of " + askingPrice;
		} else {
			player.triedToSell();
			return "Unfortunately your player didn't get bought. Lowering the asking price might increase the chances for a team buying your player.";
		}
		
		
		
		
	}
}
