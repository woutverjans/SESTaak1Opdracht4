package com.KULeuven;

import be.KULeuven.model.Candy;

import java.util.ArrayList;

public class CheckNeighboursInGrid {
    /**
     * This method takes a 1D Iterable and an element in the array and gives back an iterable containing the indexes of all neighbours with the same value as the specified element
     *@return - Returns a 1D Iterable of ints, the Integers represent the indexes of all neighbours with the same value as the specified element on index 'indexToCheck'.
     *@param grid - This is a 1D Iterable containing all elements of the grid. The elements are integers.
     *@param width - Specifies the width of the grid.
     *@param height - Specifies the height of the grid (extra for checking if 1D grid is complete given the specified width)
     *@param indexToCheck - Specifies the index of the element which neighbours that need to be checked
     */
    public static Iterable<Integer> getSameNeighboursIds(Iterable<Candy> grid, int width, int height, int indexToCheck){
        //Van voorbeeld op site SES: index 0 is links boven in de grid
        ArrayList<Candy> result = new ArrayList<>();
        ArrayList<Candy> gridList = new ArrayList<>();

        for (Candy element : grid) { //Zet grid om van een Iterable naar een ArrayList
            gridList.add(element);
        }

        // Check element to the left
        if (indexToCheck % width != 0 && gridList.get(indexToCheck).equals(gridList.get(indexToCheck - 1))) {
            //result.add(indexToCheck - 1);
        }

        // Check element to the right
        if (indexToCheck % width != width - 1 && gridList.get(indexToCheck).equals(gridList.get(indexToCheck + 1))) {
            //result.add(indexToCheck + 1);
        }

        // Check element above
        if (indexToCheck >= width && gridList.get(indexToCheck).equals(gridList.get(indexToCheck - width))) {
            //result.add(indexToCheck - width);
        }

        // Check element below
        if (indexToCheck + width < gridList.size() && gridList.get(indexToCheck).equals(gridList.get(indexToCheck + width))) {
            //result.add(indexToCheck + width);
        }

        // Check element top left
        if (indexToCheck >= width && indexToCheck % width != 0 && gridList.get(indexToCheck).equals(gridList.get(indexToCheck - width - 1))) {
            //result.add(indexToCheck - width - 1);
        }

        //return result;
        return null;
    }
}