package com.common.converters;

import org.primefaces.model.SortOrder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author efathi
 */
public class SortConverter {

  static public javax.swing.SortOrder convertPrimefacesToJavaSort(SortOrder pSortOrder) {
    javax.swing.SortOrder jSortOrder = javax.swing.SortOrder.UNSORTED;
    if (pSortOrder == SortOrder.ASCENDING) {
      jSortOrder = javax.swing.SortOrder.ASCENDING;
    } else if (pSortOrder == SortOrder.DESCENDING) {
      jSortOrder = javax.swing.SortOrder.DESCENDING;
    }
    return jSortOrder;
  }
}
