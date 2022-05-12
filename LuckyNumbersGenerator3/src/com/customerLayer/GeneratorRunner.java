package com.customerLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


public class GeneratorRunner {

	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<Byte>> arrayListOfWinningNums = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("./Resources/winningNums.text"));
            while(in.ready()) {
                String line = in.readLine();
                String[] parts = line.split("	");
                ArrayList<Byte> lineList = new ArrayList<>();
                for (String s : parts) {
                    lineList.add(Byte.valueOf(s));
                }
                arrayListOfWinningNums.add(lineList);
            }
            in.close();
        }
        catch(Exception e) {
        }
        
        
        List<ArrayList<Byte>> copyOnWrite = new CopyOnWriteArrayList<>();
        copyOnWrite = arrayListOfWinningNums;
        
        
        
        //finding all combination in winning numbers, put their count as a value in hashMap
        HashMap<Set<Byte>, Integer> winning6 = new HashMap<>();
        HashMap<Set<Byte>, Integer> winning5 = new HashMap<>();
        HashMap<Set<Byte>, Integer> winning4 = new HashMap<>();
        HashMap<Set<Byte>, Integer> winning3 = new HashMap<>();
        HashMap<Set<Byte>, Integer> winning2 = new HashMap<>();
        HashMap<Byte, Integer> winning1 = new HashMap<>();
        int iterator = 0;
        for(ArrayList<Byte> winningNums:copyOnWrite) {
        	iterator++;
        	if(iterator >= (copyOnWrite.size()/6) && iterator < (copyOnWrite.size()/6+1) ) {
        		System.out.println("Hello and welcome!");
        		System.out.println();
        	}
        	if(iterator >= (copyOnWrite.size()/2) && iterator < (copyOnWrite.size()/2+1)) {
        		System.out.println("This is Lucky numbers generator for Sportka lotto");
                System.out.println();
        	}
        	if(iterator >= (copyOnWrite.size()*4/5) && iterator < (copyOnWrite.size()*4/5+1) ) {
        		System.out.println("This program finds possible uniqe combinations of six numbers from 1 to 49, that was not in winning numbers database");
                System.out.println();
        	}
        	Set<Byte> six = new HashSet<>();
        	for(int i = 0; i<winningNums.size(); i++) {
        		
        		six.add(winningNums.get(i));
        	}
        	Integer counter6 = winning6.get(six);
			if (counter6 == null) {
				winning6.put(six, 1);
			} else {
				winning6.put(six, (counter6 + 1));
			}
        	for(int a=0; a < winningNums.size(); a++) {
				Integer counter = winning1.get(winningNums.get(a));
    			if (counter == null) {
    				winning1.put(winningNums.get(a), 1);
    			} else {
    				winning1.put(winningNums.get(a), counter + 1);
    			}
    			for(int b=a+1; b < winningNums.size(); b++) {
    				Set<Byte> two = new HashSet<>();
					two.add(winningNums.get(a));
					two.add(winningNums.get(b));
					Integer counter2 = winning2.get(two);
	    			if (counter2 == null) {
	    				winning2.put(two, 1);
	    			} else {
	    				winning2.put(two, (counter2 + 1));
	    			}
    				for(int c=b+1; c < winningNums.size(); c++) {
    					Set<Byte> three = new HashSet<>();
    					three.add(winningNums.get(a));
    					three.add(winningNums.get(b));
    					three.add(winningNums.get(c));
    					Integer counter3 = winning3.get(three);
		    			if (counter3 == null) {
		    				winning3.put(three, 1);
		    			} else {
		    				winning3.put(three, (counter3 + 1));
		    			}
    					for(int d = c+1; d < winningNums.size(); d++) {
    						Set<Byte> four = new HashSet<>();
							four.add(winningNums.get(a));
							four.add(winningNums.get(b));
							four.add(winningNums.get(c));
							four.add(winningNums.get(d));
							Integer counter4 = winning4.get(four);
			    			if (counter4 == null) {
			    				winning4.put(four, 1);
			    			} else {
			    				winning4.put(four, (counter4 + 1));
			    			}
    						for(int e = d+1; e < winningNums.size(); e++) {
    							Set<Byte> five = new HashSet<>();
    							five.add(winningNums.get(a));
    							five.add(winningNums.get(b));
    							five.add(winningNums.get(c));
    							five.add(winningNums.get(d));
    							five.add(winningNums.get(e));
    							Integer counter5 = winning5.get(five);
    			    			if (counter5 == null) {
    			    				winning5.put(five, 1);
    			    			} else {
    			    				winning5.put(five, (counter5 + 1));
    			    			}
    						}
    					}
    				}
    			}
    		}
    	}
        //====================End
        
        
        //====================Gathering data to print
        int numMax = winning1.values().stream().reduce(0, Math::max);
        double numAvarageUse = winning1.values().stream().reduce(0, Integer::sum)*1D/winning1.size();
        int numMin = winning1.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniqueNum = 49-winning1.size();
        
        double pairAvarageUse = winning2.values().stream().reduce(0, Integer::sum)*1D/winning2.size();
        int pairMax = winning2.values().stream().reduce(0, Math::max);
        int pairMin = winning2.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniquePairs = possibleComb(2)-winning2.size();
        
        double threeAvarageUse = winning3.values().stream().reduce(0, Integer::sum)*1D/winning3.size();
        int threeMax = winning3.values().stream().reduce(0, Math::max);
        int triplesMin = winning3.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniqueTriples = possibleComb(3)-winning3.size();
        
        double fourAvarageUse = winning4.values().stream().reduce(0, Integer::sum)*1D/winning4.size();
        int fourMax = winning4.values().stream().reduce(0, Math::max);
        int fourMin = winning4.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniqueQuad = possibleComb(4)-winning4.size();
        
        double fiveAvarageUse = winning5.values().stream().reduce(0, Integer::sum)*1D/winning5.size();
        int fiveMax = winning5.values().stream().reduce(0, Math::max);
        int fiveMin = winning5.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniquePentad = possibleComb(5)-winning5.size();
        
        double sixAvarageUse = winning6.values().stream().reduce(0, Integer::sum)*1D/winning6.size();
        int sixMax = winning6.values().stream().reduce(0, Math::max);
        int sixMin = winning6.values().stream().reduce(Integer.MAX_VALUE, Math::min);
        int uniqueCombinations = possibleComb(6)-winning6.size();
        //====================End
        
        System.out.println();
        System.out.printf("Size of database is: %d records",arrayListOfWinningNums.size()).println();
        System.out.println();
        
        //====================Print Data from database
        System.out.println("  Number of element used | Number of unique element | Minimal element use | Avarage Element use | Maximal element use");
        System.out.printf ("  Number:             %s |                        %s |                 %s |                 %s |                 %s", winning1.size(), uniqueNum , numMin, Math.round(numAvarageUse), numMax).println();
        System.out.printf ("  Pairs:            %s |                        %s |                  %s |                  %s |                %s", winning2.size(), uniquePairs , pairMin, Math.round(pairAvarageUse), pairMax).println();
        System.out.printf("  Triples:         %s |                       %s |                   %s |                   %s |                  %s", winning3.size(), uniqueTriples , triplesMin, Math.round(threeAvarageUse), threeMax).println();
        System.out.printf("  Quadruplet:      %s |                   %s |                   %s |                   %s |                   %s", winning4.size(), uniqueQuad , fourMin, Math.round(fourAvarageUse), fourMax).println();
        System.out.printf("  Pentad:          %s |                  %s |                   %s |                   %s |                   %s", winning5.size(), uniquePentad , fiveMin, Math.round(fiveAvarageUse), fiveMax).println();
        System.out.printf("Winning numbers:    %s |                  %s |                   %s |                   %s |                   %s", winning6.size(), uniqueCombinations, sixMin, Math.round(sixAvarageUse), sixMax).println();
        
        short luckyNumsMinUse = 0;
        int luckyNumsMaxUse = numMax;
        short lucky2minUse = 0;
        int lucky2maxUse = pairMax;
        short lucky3minUse = 0;
        int lucky3maxUse = threeMax;
        short lucky4minUse = 0;
        int lucky4maxUse = fourMax;
        boolean useUnique5 = false;
        
        System.out.println();
        System.out.println("Do you want to add more criteria? (Y - yes please/ N - no thanks)");
        Scanner scanner = new Scanner(System.in, "Windows-1250");
        String moreCriteriaYN = scanner.nextLine();
        if(moreCriteriaYN.equalsIgnoreCase("y")) {
        	System.out.println("Do you want to pick only from some of lucky numbers?(Y - yes please/ N - no thanks)");
        	String evaluateNums = scanner.nextLine();
        	if(evaluateNums.equalsIgnoreCase("y")) {
        		System.out.println("What is minimal usege of number, that you want to include?");
        		String numFloor = scanner.nextLine();
        		
        		if(Short.parseShort(numFloor) < numMax && Short.parseShort(numFloor) >= 0) {
        			luckyNumsMinUse =  Short.parseShort(numFloor);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		System.out.println("What is maximal usege of number, that you want to include?");
        		String numCeiling = scanner.nextLine();
        		if(Short.parseShort(numCeiling) >= 0) {
        			lucky2maxUse =  Short.parseShort(numCeiling);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		
        	}
        	System.out.println("Do you want to pick only from some of lucky pairs?(Y - yes please/ N - no thanks)");
        	String evaluatePair = scanner.nextLine();
        	if(evaluatePair.equalsIgnoreCase("y")) {
        		System.out.println("What is minimal usege of pair, that you want to include?");
        		String pairFloor = scanner.nextLine();
        		
        		if(Short.parseShort(pairFloor) < pairMax && Short.parseShort(pairFloor) >= 0) {
        			lucky2minUse =  Short.parseShort(pairFloor);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		System.out.println("What is maximal usege of pair, that you want to include?");
        		String pairCeiling = scanner.nextLine();
        		if(Short.parseShort(pairCeiling) >= 0) {
        			lucky2maxUse =  Short.parseShort(pairCeiling);
        			
        		}else {
        			System.out.println("Incompatible input");
        		}
        		
        	}
        	System.out.println("Do you want to pick only from some of lucky triples?(Y - yes please/ N - no thanks)");
        	String evaluateTriples = scanner.nextLine();
        	if(evaluateTriples.equalsIgnoreCase("y")) {
        		System.out.println("What is minimal usege of triple, that you want to include? (Note, if you use more than 0, unique triples won't be used)");
        		String triplesFloor = scanner.nextLine();
        		
        		if(Short.parseShort(triplesFloor) < threeMax && Short.parseShort(triplesFloor) >= 0) {
        			lucky3minUse =  Short.parseShort(triplesFloor);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		System.out.println("What is maximal usege of triples, that you want to include?");
        		String triplesCeiling = scanner.nextLine();
        		if(Short.parseShort(triplesCeiling) >= 0) {
        			lucky3maxUse =  Short.parseShort(triplesCeiling);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		
        	}
        	System.out.println("Do you want to pick only from some of lucky quadruplet?(Y - yes please/ N - no thanks)");
        	String evaluateQuadruplet = scanner.nextLine();
        	if(evaluateQuadruplet.equalsIgnoreCase("y")) {
        		System.out.println("What is minimal usege of quadruplet, that you want to include? (Note, if you use more than 0, unique quadruplets wont be used)");
        		String quadFloor = scanner.nextLine();
        		
        		if(Short.parseShort(quadFloor) < pairMax && Short.parseShort(quadFloor) >= 0) {
        			lucky4minUse =  Short.parseShort(quadFloor);
        		}else {
        			System.out.println("Incompatible input");
        		}
        		System.out.println("What is maximal usege of pair, that you want to use?");
        		String quadCeiling = scanner.nextLine();
        		if(Short.parseShort(quadCeiling) >= 0) {
        			lucky4maxUse =  Short.parseShort(quadCeiling);
        		}else {
        			System.out.println("Incompatible input");
        		}
        	}
        	System.out.println("Do you want to pick only unique pentads?(Y - yes please/ N - no thanks)");
        	String excludeLuckyPentad = scanner.nextLine();
        	if(excludeLuckyPentad.equalsIgnoreCase("y")) {
        		useUnique5 = true;
        	}
        	
        	
        	
        }
        
        final short luckyNumsMinUseFinal = luckyNumsMinUse;
        final int luckyNumsMaxUseFinal = luckyNumsMaxUse;
		final short lucky2minUseFinal =  lucky2minUse;
		final int lucky2maxUseFinal = lucky2maxUse;
		final short lucky3minUseFinal =  lucky3minUse;
		final int lucky3maxUseFinal = lucky3maxUse;
		final short lucky4minUseFinal =  lucky4minUse;
		final int lucky4maxUseFinal = lucky4maxUse;
        
        boolean useLucky2;
        if(lucky2maxUse > lucky2minUse) {
        	useLucky2 = true;
      	}else {
      		useLucky2 = false;
      	}
        
        boolean useUnique3;
        boolean useLucky3;
        if(lucky3minUse == 0) {
      			useUnique3 = true;
        }else {
      			useUnique3 = false;
        }
        
        if(lucky3maxUse > lucky3minUse) {
      			useLucky3 = true;
        }else {
      			useLucky3 = false;
        }
        
        boolean useLucky4;
        boolean useUnique4;
        if(lucky4minUse == 0) {
      			useUnique4 = true;
        }else {
      			useUnique4 = false;
        }
        if(lucky4maxUse > lucky4minUse) {
      			useLucky4 = true;
        }else {
      			useLucky4 = false;
        }
        
        
        
        //====================PairOperations
        HashSet<Byte> lucky1 = new HashSet<>();
        winning1.keySet().stream().filter(a -> (winning1.get(a) >= luckyNumsMinUseFinal && winning1.get(a) <= luckyNumsMaxUseFinal)).forEach(a -> lucky1.add(a));
        
        System.out.printf("Number of lucky pairs is: %s", lucky1.size()).println();
        
        
        HashSet<Set<Byte>> lucky2 = new HashSet<>();
        winning2.keySet().stream().filter(a -> (winning2.get(a) >= lucky2minUseFinal && winning2.get(a) <= lucky2maxUseFinal)).forEach(a -> lucky2.add(a));
        
        System.out.printf("Number of lucky pairs is: %s", lucky2.size()).println();
        //====================End
        
        
        //====================TriplesOperations
        HashSet<Set<Byte>> lucky3 = new HashSet<>();
        winning3.keySet().stream().filter(a -> (winning3.get(a) >= lucky3minUseFinal && winning3.get(a) <= lucky3maxUseFinal)).forEach(a -> lucky3.add(a));
        if(useUnique3) {
        	System.out.printf("Number of lucky triples is: %s", lucky3.size()+possibleComb(3)-winning3.size()).println();
        }else {
        System.out.printf("Number of lucky triples is: %s", lucky3.size()).println();
        }
        //====================End
        
        
        //====================QuadruplesOperations
        HashSet<Set<Byte>> lucky4 = new HashSet<>();
        winning4.keySet().stream().filter(a -> (winning4.get(a) >= lucky4minUseFinal && winning4.get(a) <= lucky4maxUseFinal)).forEach(a -> lucky4.add(a));
        if(useUnique4) {
        	System.out.printf("Number of lucky quadruplets is: %s", lucky4.size()+possibleComb(4)-winning4.size()).println();
        }else {
        System.out.printf("Number of lucky quadruplets is: %s", lucky4.size()).println();
        }
        
        //====================End
        
        
        //====================SextetOperations
        
        //====================End
        
        //====================Generator of all possible combinations
        HashSet<HashSet<Byte>> possibleComb = possibleCombGenerator(lucky2, lucky3, lucky4, winning1, winning2, winning3, winning4, winning5, winning6, useLucky2, useLucky3, useUnique3, useLucky4, useUnique4, useUnique5, luckyNumsMinUse, luckyNumsMaxUse);
        System.out.printf("Possible combinations with given criterie is: %s", possibleComb.size()).println();
		
        System.out.println(checkUseOfAllNums(possibleComb));
		
		//====================End
        
        //====================scannerForTickets
        int numberOfTickets = 0;
        
        System.out.println();

        System.out.println("How many tickets you wish to pick?");
        String howManyTicketsSc = scanner.nextLine();
        numberOfTickets = Integer.parseInt(howManyTicketsSc);
        if(numberOfTickets > possibleComb.size() || numberOfTickets < 0) {
        	System.out.println("Invalid input");
        	numberOfTickets=0;
        }
        scanner.close();
        
        //====================printer
		for(int i = 1; i <= numberOfTickets; i++) {
			System.out.println(randomizer(possibleComb));
		}
        //====================End
        
		
		/* =========================== print possibleSix with the highest score and dont use number twice
		HashMap<Set<Byte>, Double> scoredPossibilities = new HashMap<>();
		TreeMap<Double, Set<Byte>> reversedScoredPossibilities = new TreeMap<>();
		for(HashSet<Byte> possibleSix:possibleComb) {
			double score = 0;
			for(byte elementA:possibleSix) {
				for(byte elementB:possibleSix) {
					if(elementB > elementA){
						Set<Byte> pair = new HashSet<>();
						pair.add(elementA);
						pair.add(elementB);
						if(winning2.containsKey(pair)) {
							score += winning2.get(pair)*24;
						}
						for(byte elementC:possibleSix) {
							if(elementC > elementB){
								Set<Byte> three = new HashSet<>();
								three.add(elementA);
								three.add(elementB);
								three.add(elementC);
								if(winning3.containsKey(three)) {
									score += winning3.get(three)*24*15;
								}
								for(byte elementD:possibleSix) {
									if(elementD > elementC) {
										for(byte elementE:possibleSix){
											if(elementE > elementD) {
												for(byte elementF:possibleSix) {
													if(elementF > elementE) {
														Set<Byte> sixPlusScore = new HashSet<>();
														sixPlusScore.add(elementA);
														sixPlusScore.add(elementB);
														sixPlusScore.add(elementC);
														sixPlusScore.add(elementD);
														sixPlusScore.add(elementE);
														sixPlusScore.add(elementF);
														scoredPossibilities.put(sixPlusScore, score);
														if(reversedScoredPossibilities.containsKey(score)) {
															score = score+Math.random();
														}
														reversedScoredPossibilities.put(score, sixPlusScore);
													}	
        										}
        									}
        								}
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }

		HashSet<Byte> numbersToUse = new HashSet<>();
		fillPossibleNums(numbersToUse);
		for(int i = 1; i <= howManytickets; i++) {
			if(reversedScoredPossibilities.lastEntry() != null) {
				if(numbersToUse.size() == 0) {
					fillPossibleNums(numbersToUse);
				}
				Set<Byte> toPrint = reversedScoredPossibilities.lastEntry().getValue();
				Double toPrintScore = reversedScoredPossibilities.lastEntry().getKey();
				if(numbersToUse.size() >= 6) {
					if(numbersToUse.containsAll(toPrint)) {
						System.out.println(toPrint);
						numbersToUse.removeAll(toPrint);
						System.out.println(numbersToUse.size());
						reversedScoredPossibilities.remove(toPrintScore);
					}else {
						reversedScoredPossibilities.remove(toPrintScore);
					}
				}else {
					if(toPrint.containsAll(numbersToUse)){
						if(numbersToUse.containsAll(toPrint)) {
							System.out.println(toPrint);
							fillPossibleNums(numbersToUse);
							numbersToUse.removeAll(toPrint);
							System.out.println(numbersToUse.size());
							reversedScoredPossibilities.remove(toPrintScore);
						}
					}else {
						reversedScoredPossibilities.remove(toPrintScore);
					}			
				}
			}
		}
		*/
	}
	//========================Methods
	
	public static void fillPossibleNums(HashSet<Byte> numbersToUse) {
		for(byte i = 1; i <= 49; i++) {
			numbersToUse.add(i);
		}
	}
	
	public static int possibleComb(int combLength) {
		int possibleComb = 0;
		int divident = 1;
		int divisor = 1;
		for(int i = 49; i > 49-combLength; i--) {
			divident = divident * i;
		}
		for(int i = 1; i <= combLength; i++) {
			divisor = divisor * i;
		}
		possibleComb = divident/divisor;
		return possibleComb;
	}
	
	public static HashSet<HashSet<Byte>> possibleCombGenerator (HashSet<Set<Byte>> lucky2, HashSet<Set<Byte>> lucky3, HashSet<Set<Byte>>lucky4, 
			HashMap<Byte, Integer> winning1, HashMap<Set<Byte>, Integer> winning2, HashMap<Set<Byte>, Integer> winning3, HashMap<Set<Byte>, Integer> winning4, HashMap<Set<Byte>, Integer> winning5, HashMap<Set<Byte>, Integer> winning6, 
			boolean useLucky2, boolean useLucky3, boolean useUnique3, boolean useLucky4, boolean useUnique4, boolean useUnique5, short luckyNumsMinUse, int luckyNumsMaxUse){
		HashSet<HashSet<Byte>> possibleComb = new HashSet<>();	
		for(byte a=1; a <= 44; a++) {
			if(check1(a, winning1, luckyNumsMinUse, luckyNumsMaxUse)) {
				
			
			for(byte b=(byte)(a+1); b <= 45; b++) { 
				HashSet<Byte> possible2 = new HashSet<>();    								
				possible2.add(a);    								
				possible2.add(b);    								  							
				if (check1(b, winning1, luckyNumsMinUse, luckyNumsMaxUse) && lucky2.contains(possible2)) {
					for(byte c=(byte)(b+1); c <= 46; c++) { 
						HashSet<Byte> possible3 = new HashSet<>();    
						possible3.add(a);    								
						possible3.add(b); 
						possible3.add(c);
						if (check1(c, winning1, luckyNumsMinUse, luckyNumsMaxUse) && check2(possible3, lucky2) && check3(possible3, winning3, lucky3, useLucky3, useUnique3)) {
							for(byte d = (byte)(c+1); d <= 47; d++) {    						
								HashSet<Byte> possible4 = new HashSet<>();    								
								possible4.add(a);    								
								possible4.add(b);    								
								possible4.add(c);    								
								possible4.add(d);    							
								if (check1(d, winning1, luckyNumsMinUse, luckyNumsMaxUse) && check2(possible4, lucky2)&& check3(possible4, winning3, lucky3, useLucky3, useUnique3)  && check4(possible4, winning4, lucky4, useLucky4, useUnique4)) {
									for(byte e = (byte)(d+1); e <= 48; e++) { 
										HashSet<Byte> possible5 = new HashSet<>();    								
										possible5.add(a);    								
										possible5.add(b);    								
										possible5.add(c);    								
										possible5.add(d);    							
										possible5.add(e);  
										if (check1(e, winning1, luckyNumsMinUse, luckyNumsMaxUse) && check2(possible5, lucky2) && check3(possible5, winning3, lucky3, useLucky3, useUnique3) && check4(possible5, winning4, lucky4, useLucky4, useUnique4) && check5v2(possible5, winning5, useUnique5)) {
											for(byte f = (byte)(e+1); f <= 49; f++) {    								
												HashSet<Byte> possibleSix = new HashSet<>();    								
												possibleSix.add(a);    								
												possibleSix.add(b);    								
												possibleSix.add(c);    								
												possibleSix.add(d);    							
												possibleSix.add(e);    							
												possibleSix.add(f);   
												if(check1(f, winning1, luckyNumsMinUse, luckyNumsMaxUse) && check2(possibleSix, lucky2) && check3(possibleSix, winning3, lucky3, useLucky3, useUnique3)  && check4(possibleSix, winning4, lucky4, useLucky4, useUnique4) && check5v2(possibleSix, winning5, useUnique5)) {
													if(!winning6.containsKey(possibleSix)) {
														possibleComb.add(possibleSix);  
														//System.out.println(possibleSix);
													}
												}
											}
										}
									}
								}
							}
						}
    				}
    			}
    		}
			}
    	}
		return possibleComb;
	}
	public static HashSet<Byte> randomizer(HashSet<HashSet<Byte>> possibleComb) {
		int counter=0;
		double randomizer=Math.random()*possibleComb.size();
		for(HashSet<Byte> possibility:possibleComb) {
			counter++;
			if(counter>randomizer && counter<randomizer+1) {
				possibleComb.remove(possibility);
				return possibility;
			}
		}
		return null;
	}
	
	
	public static boolean check1(byte element, HashMap<Byte, Integer> winning1, short luckyNumsMinUse, int luckyNumsMaxUse) {
				if(winning1.get(element) < luckyNumsMinUse || winning1.get(element) > luckyNumsMaxUse) {
					return false;
				}
		return true;
	}
	
	public static boolean check2(HashSet<Byte> possibleSet, HashSet<Set<Byte>> lucky2) {
		
		for(Byte elementA:possibleSet) {
			for(Byte elementB:possibleSet) {
				if(elementB > elementA) {
					HashSet<Byte> pair = new HashSet<>();
					pair.add(elementA.byteValue());
					pair.add(elementB.byteValue());
					if(!lucky2.contains(pair)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean check3(HashSet<Byte> possibleSet, HashMap<Set<Byte>, Integer> winning3, HashSet<Set<Byte>> lucky3, boolean useLucky3, boolean useUnique3) {
		for(Byte elementA:possibleSet) {
			for(Byte elementB:possibleSet) {
				if(elementB > elementA) {
					for(Byte elementC:possibleSet) {
						if(elementC > elementB) {
							HashSet<Byte> three = new HashSet<>();
							three.add(elementA.byteValue());
							three.add(elementB.byteValue());
							three.add(elementC.byteValue());
							return(condition3(three, winning3, lucky3, useLucky3, useUnique3));
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean condition3(HashSet<Byte> three, HashMap<Set<Byte>, Integer> winning3, HashSet<Set<Byte>> lucky3, boolean useLucky3, boolean useUnique3) {
		if(useLucky3 && useUnique3) {
			return(lucky3.contains(three) || !winning3.containsKey(three));
		}else if(useLucky3) {
			return(lucky3.contains(three));
		}else if(useUnique3) {
			return !winning3.containsKey(three);
		}
		return false;
	}
	
	public static boolean check4(HashSet<Byte> possibleSet, HashMap<Set<Byte>, Integer> winning4, HashSet<Set<Byte>> lucky4, boolean useLucky4, boolean useUnique4) {
		for(Byte elementA:possibleSet) {
			for(Byte elementB:possibleSet) {
				if(elementB.byteValue()>elementA.byteValue()) {
					for(Byte elementC:possibleSet) {
						if(elementC.byteValue()>elementB.byteValue()) {
							for(Byte elementD:possibleSet) {
								if(elementD.byteValue()>elementC.byteValue()) {
									HashSet<Byte> four = new HashSet<>();
									four.add(elementA.byteValue());
									four.add(elementB.byteValue());
									four.add(elementC.byteValue());
									four.add(elementD.byteValue());
									return (condition4(four, winning4, lucky4, useLucky4, useUnique4));
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean condition4(HashSet<Byte> four, HashMap<Set<Byte>, Integer> winning4, HashSet<Set<Byte>> lucky4, boolean useLucky4, boolean useUnique4) {
		if(useLucky4 && useUnique4) {
			return(lucky4.contains(four) || !winning4.containsKey(four));
		}else if(useLucky4) {
			return(lucky4.contains(four));
		}else if(useUnique4) {
			return !winning4.containsKey(four);
		}
		return false;
	}
	
	public static boolean check5v2(HashSet<Byte> possibleSet, HashMap<Set<Byte>, Integer> winning5, boolean useUnique5) {
		if(useUnique5) {
			for(Byte elementA:possibleSet) {
				for(Byte elementB:possibleSet) {
					if(elementB.byteValue()>elementA.byteValue()) {
						for(Byte elementC:possibleSet) {
							if(elementC.byteValue()>elementB.byteValue()) {
								for(Byte elementD:possibleSet) {
									if(elementD.byteValue()>elementC.byteValue()) {
										for(Byte elementE:possibleSet) {
											if(elementE.byteValue()>elementD.byteValue()) {									
												HashSet<Byte> five = new HashSet<>();
												five.add(elementA.byteValue());
												five.add(elementB.byteValue());
												five.add(elementC.byteValue());
												five.add(elementD.byteValue());
												five.add(elementE.byteValue());
												return !winning5.containsKey(five);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}else {
			return true;
		}
		return true;
	}
	
	public static boolean checkUseOfAllNums(HashSet<HashSet<Byte>> possibleComb) {
		HashSet<Byte> usedNumbers = new HashSet<>();
		for(HashSet<Byte> possibleCombination:possibleComb) {
			for(Byte number:possibleCombination) {
				usedNumbers.add(number.byteValue());
				if(usedNumbers.size() >= 49) {
					return true;
				}
			}
		}
		return false;
	}
	
}
