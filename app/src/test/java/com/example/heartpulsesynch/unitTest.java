package com.example.heartpulsesynch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * A class containing unit tests for the {@link RecordList} class.
 */
public class unitTest {

    /**
     * Tests the {@link RecordList#addRecord(ModelClass)} method.
     */
    @Test
    public void testAddRecord() {
        RecordList recordList = new RecordList();
        ModelClass modelClass = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(modelClass);
        assertEquals(1, recordList.mcl.size());

        ModelClass modelClass1 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(modelClass1);
        assertEquals(2, recordList.mcl.size());
        assertTrue(recordList.mcl.contains(modelClass));
        assertTrue(recordList.mcl.contains(modelClass1));
    }

    /**
     * Tests the {@link RecordList#addRecord(ModelClass)} method when adding a duplicate record.
     */
    @Test
    public void addRecordExTest() {
        RecordList recordList = new RecordList();
        ModelClass modelClass = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(modelClass);
        assertThrows(IllegalArgumentException.class, () -> recordList.addRecord(modelClass));
    }

    /**
     * Tests the {@link RecordList#deleteRecord(int)} method.
     */
    @Test
    public void testDeleteRecord() {
        RecordList recordList = new RecordList();
        ModelClass record1 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record1);
        assertEquals(1, recordList.mcl.size());

        ModelClass record2 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record2);
        assertEquals(2, recordList.mcl.size());

        ModelClass record3 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record3);
        assertEquals(3, recordList.mcl.size());

        assertTrue(recordList.mcl.contains(record1));
        assertTrue(recordList.mcl.contains(record2));

        recordList.deleteRecord(0);
        assertEquals(2, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record1));

        recordList.deleteRecord(0);
        assertEquals(1, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record2));

        assertThrows(IllegalArgumentException.class, () -> recordList.deleteRecord(1));
    }

    /**
     * Tests the {@link RecordList#count()} method.
     */
    @Test
    public void testCount(){
        RecordList recordList=new RecordList();
        ModelClass modelClass1=new ModelClass("1-1-2000","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass1);
        assertEquals(1,recordList.count());

    }
}