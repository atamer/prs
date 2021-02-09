package com.imc.prs.domain.hands;

/*If comparing logic changes or new comparing logic added , we can define new strategy here */
public enum HandComparingStrategy {


    // If firstHand wins returns number > 0 ,
    // if secondHand wins returns number < 0,
    // if draw returns 0
    NATURAL {
        @Override
        public int compareHands(Hands firstHand, Hands secondHand) {
            if (firstHand == null)
                throw new IllegalArgumentException("first hand can not be null");
            if (secondHand == null)
                throw new IllegalArgumentException("second hand can not be null");
            if (firstHand == Hands.QUIT || secondHand == Hands.QUIT)
                throw new IllegalArgumentException("can not compare QUIT");


            int diff = firstHand.weight - secondHand.weight;
            if (diff == 1 || diff == -1) {
                return diff;
            } else {
                return -diff;
            }
        }
    };

    public abstract int compareHands(Hands firstHand, Hands secondHand);


}
