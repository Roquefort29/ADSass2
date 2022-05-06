public class Main {

    public static void main(String[] args) {
        SyrHashTable syrprint = new SyrHashTable(6);

        //I ADDED ELEMENTS TO ARRAY
        syrprint.addElement(22);
        syrprint.addElement(33);
        syrprint.addElement(44);
        syrprint.addElement(55);
        syrprint.addElement(66);
        syrprint.addElement(77);

        //I CREATED TABLE WITH THIS ELEMENTS
        syrprint.printHashTable();
        System.out.println(syrprint.EmptyOrNot());

        //I SHOWED THAT I ADDED ELEMENTS AND DELETED TO THE ARRAYS
        syrprint.addElement(12);
        syrprint.addElement(31);
        syrprint.deleteElement(66);

        syrprint.printHashTable();
        System.out.println(syrprint.EmptyOrNot());

        // I DELETED EVERY COLUMN
        syrprint.delete();

        syrprint.printHashTable();

        //I CHECKED THE LAST ONE EMPTY TABLE OR NOT
        System.out.println(syrprint.EmptyOrNot());
    }
}


public class SyrHashTable {
    linkedList[] syrHashTable;
    int size;

    SyrHashTable(int hSize){
        syrHashTable = new linkedList[hSize];
        size = 0;
    }

    public boolean EmptyOrNot() { //check table is empty or not
        return size == 0;
    }

    public void delete()
    {
        int dlina = syrHashTable.length;
        syrHashTable = new linkedList[dlina];
        size = 0;
    }

    // getter of Size
    public int getSize() {
        return size;
    }

    public void addElement(int valueeeee) {
        size++;
        int place = hash(valueeeee);

        linkedList n = new linkedList(valueeeee);

        linkedList start = syrHashTable[place];

        if (syrHashTable[place] == null) {
            syrHashTable[place] = n;
        }
        else {
            n.first = start;
            start.second = n;
            syrHashTable[place] = n;
        }
    }

    public void deleteElement(int valueeeee)
    {
        try {
            int place = hash(valueeeee);

            linkedList start = syrHashTable[place];

            linkedList finish = start;

            if (start.num == valueeeee) {
                size--;
                if (start.first == null) {
                    syrHashTable[place] = null;
                }

                start = start.first;
                start.second = null;
                syrHashTable[place] = start;
            }
            while (finish.first != null && finish.first.num != valueeeee)
                finish = finish.first;

            // if reached at the end without finding the
            // value
            if (finish.first == null) {
                System.out.println("\nElement not found\n");
                return;
            }

            size--;

            if (finish.first.first == null) {
                finish.first = null;
                return;
            }

            finish.first.first.second = finish;
            finish.first = finish.first.first;

            syrHashTable[place] = start;
        }
        catch (Exception e) {
            System.out.println("\nElement not found\n");
        }
    }

    // Definition of Hash function
    private int hash(Integer anyelem)
    {
        int hashValue = anyelem.hashCode();

        hashValue %= syrHashTable.length;

        if (hashValue < 0)
            hashValue += syrHashTable.length;

        return hashValue;
    }

    // Function to print hash table
    public void printHashTable()
    {
        System.out.println();
        for (int i = 0; i < syrHashTable.length; i++) {
            System.out.print(i + ":  ");

            linkedList start = syrHashTable[i];

            while (start != null) {
                System.out.print(start.num + " ");
                start = start.first;
            }

            System.out.println();
        }
    }
}

public class linkedList {
    linkedList first, second;
    int num;

    public linkedList(int num) {
        this.num = num;
    }
}