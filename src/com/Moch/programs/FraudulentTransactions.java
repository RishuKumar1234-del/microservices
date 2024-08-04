package com.Moch.programs;

import java.util.*;

public class FraudulentTransactions {

    static class Transaction {
        String id;
        int amount;
        String city;
        long timestamp;
        
        Transaction(String id, int amount, String city, long timestamp) {
            this.id = id;
            this.amount = amount;
            this.city = city;
            this.timestamp = timestamp;
        }
    }

    public static List<String> findFraudulentTransactions(List<Transaction> transactions) {
        List<String> fraudulentIds = new ArrayList<>();
        Map<String, List<Transaction>> cardTransactions = new HashMap<>();
        
        
        for (Transaction transaction : transactions) {
            cardTransactions.computeIfAbsent(transaction.id, k -> new ArrayList<>()).add(transaction);
        }
        
        
        for (Transaction transaction : transactions) {
            if (transaction.amount >= 10000) {
                fraudulentIds.add(transaction.id);
            }
        }
        
        
        for (List<Transaction> cardTxns : cardTransactions.values()) {
           
            cardTxns.sort(Comparator.comparingLong(txn -> txn.timestamp));
            
            for (int i = 0; i < cardTxns.size(); i++) {
                Transaction txn1 = cardTxns.get(i);
                for (int j = i + 1; j < cardTxns.size(); j++) {
                    Transaction txn2 = cardTxns.get(j);
                    
                   
                    if (txn2.timestamp - txn1.timestamp <= 1800000 && !txn1.city.equals(txn2.city)) {
                        if (!fraudulentIds.contains(txn1.id)) fraudulentIds.add(txn1.id);
                        if (!fraudulentIds.contains(txn2.id)) fraudulentIds.add(txn2.id);
                    }
                }
            }
        }
        
        return fraudulentIds;
    }

    public static void main(String[] args) {
        
        List<Transaction> transactions = Arrays.asList(
            new Transaction("T1", 12000, "New York", 1682131200000L), 
            new Transaction("T2", 8000, "San Francisco", 1682133600000L),
            new Transaction("T3", 5000, "New York", 1682137200000L),
            new Transaction("T4", 3000, "Los Angeles", 1682138400000L),
            new Transaction("T5", 15000, "San Francisco", 1682139300000L),
            new Transaction("T6", 2000, "San Francisco", 1682140800000L),
            new Transaction("T7", 12000, "Los Angeles", 1682143200000L) 
        );

        List<String> fraudulentTransactions = findFraudulentTransactions(transactions);
        System.out.println("Fraudulent Transactions IDs: " + fraudulentTransactions);
    }
}
