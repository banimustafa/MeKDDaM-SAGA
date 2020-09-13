/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.datatools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import process_model.basic.util.DateTime;
import toolbox.Tools;
import weka.attributeSelection.AttributeSelection;
import weka.core.Instances;
import weka.filters.unsupervised.instance.Resample;
import weka.filters.unsupervised.attribute.*;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.unsupervised.instance.Randomize;
/**
 *
 * @author amb04
 */
public class FilteringTools
{


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Centering
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances center(Instances inputDataInstances )
  {
        try {

            Center center = new Center();
            center.setInputFormat(inputDataInstances);

            Instances resultDataInstances = Center.useFilter(inputDataInstances, center);

            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Centering
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances center(Instances inputDataInstances, String optionsString )
  {
        try {
            if (optionsString==null)
                optionsString=" ";
            //
            Center center = new Center();
            center.setOptions(Tools.stringToArray(optionsString));
            center.setInputFormat(inputDataInstances);

            Instances resultDataInstances = Center.useFilter(inputDataInstances, center);

            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data NumericToNominal
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances numericToNominal(Instances inputDataInstances, int attributesIndex )
  {
        try {
            int[] attributesArray={attributesIndex};
            //
            NumericToNominal numericToNominal = new NumericToNominal();
            numericToNominal.setInputFormat(inputDataInstances);
            numericToNominal.setAttributeIndicesArray(attributesArray);
            //
            Instances resultDataInstances = NumericToNominal.useFilter(inputDataInstances, numericToNominal);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Discretize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances discretize(String optionsString,Instances inputInstances, int attributesIndex)
  {
        try {

            if (optionsString==null)
                optionsString=" ";

            int[] attributesArray={attributesIndex};
            //
            Discretize discretize = new Discretize();
            discretize.setAttributeIndicesArray(attributesArray);
            discretize.setInputFormat(inputInstances);
            //
            discretize.setOptions(Tools.stringToArray(optionsString));
            //
            discretize.setUseBetterEncoding(true);
            discretize.setUseKononenko(true);
            //
            Instances resultDataInstances = Discretize.useFilter(inputInstances, discretize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Discretize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances discretize(Instances inputInstances, int attributesIndex)
  {
        try {
            int[] attributesArray={attributesIndex};
            //
            Discretize discretize = new Discretize();
            discretize.setAttributeIndicesArray(attributesArray);
            discretize.setInputFormat(inputInstances);
            //
            discretize.setUseBetterEncoding(true);
            discretize.setUseKononenko(true);
            //
            Instances resultDataInstances = Discretize.useFilter(inputInstances, discretize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data binarize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances binarize(Instances inputInstances, int attributesIndex)
  {
        try {
            int[] attributesArray={attributesIndex};
            //
            Discretize discretize = new Discretize();
            discretize.setAttributeIndicesArray(attributesArray);
            discretize.setInputFormat(inputInstances);
            //
            discretize.setUseBetterEncoding(true);
            discretize.setUseKononenko(true);
            //
            discretize.setMakeBinary(true);
            //
            Instances resultDataInstances = Discretize.useFilter(inputInstances, discretize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



/*

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Discretize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances discretize(String optionsString,Instances inputInstances, int[] attributesArray,int binsNumber)
  {
        try {
            if (optionsString==null)
                optionsString=" ";

            //
            Discretize discretize = new Discretize();
            discretize.setOptions(Tools.stringToArray(optionsString));
            discretize.setInputFormat(inputInstances);
            discretize.setAttributeIndicesArray(attributesArray);
            discretize.se setBins(binsNumber);

            Instances resultDataInstances = Discretize.useFilter(inputInstances, discretize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

*/


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Standardize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances standardize(Instances inputDataInstances)
  {
        try {

            Standardize standardize = new Standardize();
            standardize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Standardize.useFilter(inputDataInstances, standardize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Standardize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances standardize(Instances inputDataInstances, String optionsString )
  {
        try {
            if (optionsString==null)
                optionsString=" ";
            
            //
            Standardize standardize = new Standardize();
            standardize.setOptions(Tools.stringToArray(optionsString));
            standardize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Standardize.useFilter(inputDataInstances, standardize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Normalize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances normalize(Instances inputDataInstances)
  {
        try {

            Normalize normalize = new Normalize();

            normalize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Normalize.useFilter(inputDataInstances, normalize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Normalize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances normalize(Instances inputDataInstances, String optionsString )
  {
        try {
            if (optionsString==null)
                optionsString=" ";
            
            //
            Normalize normalize = new Normalize();
            normalize.setOptions(Tools.stringToArray(optionsString));
            normalize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Normalize.useFilter(inputDataInstances, normalize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data NumericToNominal
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances resample(Instances inputDataInstances )
  {
        try {

            Resample resample = new Resample();
            resample.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Resample.useFilter(inputDataInstances, resample);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data NumericToNominal
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances resample(Instances inputDataInstances, String optionsString )
  {
        try {
            if (optionsString==null)
                optionsString=" ";
            
            Resample resample = new Resample();
            resample.setOptions(Tools.stringToArray(optionsString));
            resample.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Resample.useFilter(inputDataInstances, resample);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


  //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Randomize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances randomize(Instances inputDataInstances, String optionsString )
  {
        try {
            if (optionsString==null)
                optionsString=" ";
            
            //
            Randomize randomize = new Randomize();
            randomize.setRandomSeed(new DateTime().getSecond());
            randomize.setOptions(Tools.stringToArray(optionsString));
            randomize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Randomize.useFilter(inputDataInstances, randomize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data Randomize
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances randomize(Instances inputDataInstances)
  {
        try {

            Randomize randomize = new Randomize();
            randomize.setRandomSeed(new DateTime().getSecond());
            randomize.setInputFormat(inputDataInstances);
            Instances resultDataInstances = Randomize.useFilter(inputDataInstances, randomize);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Replace Missing Values
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances replaceMissingValues(Instances inputDataInstances, String optionsString)
  {
        try {
            if (optionsString==null)
                optionsString=" ";

            ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();

            replaceMissingValues.setOptions(Tools.stringToArray(optionsString));
            replaceMissingValues.setInputFormat(inputDataInstances);
            Instances resultDataInstances = ReplaceMissingValues.useFilter(inputDataInstances, replaceMissingValues);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Replace Missing Values
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances replaceMissingValues(Instances inputDataInstances)
  {
        try {

            ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();
            //
            replaceMissingValues.setInputFormat(inputDataInstances);
            Instances resultDataInstances = ReplaceMissingValues.useFilter(inputDataInstances, replaceMissingValues);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Reduce Dimenmtionality Attribute Selection
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances reduceDimensionality(Instances inputDataInstances)
  {
        try {
            
            AttributeSelection attributeSelection = new AttributeSelection();

            attributeSelection.setRanking(true);
            attributeSelection.SelectAttributes(inputDataInstances);
            Instances reduceDimensionality = attributeSelection.reduceDimensionality(inputDataInstances);
            //
            return reduceDimensionality;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                  Reduce Dimenmtionality Attribute Selection
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances reduceDimensionality(File dataSet, boolean isClassAttributeLast)
  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataSet.getPath()));
            Instances inputDataInstances = new Instances(reader);
            reader.close();
            if (isClassAttributeLast) {
                inputDataInstances.setClassIndex(inputDataInstances.numAttributes() - 1);
            } else {
                inputDataInstances.setClassIndex(0);
            }
            //
            AttributeSelection attributeSelection = new AttributeSelection();
            attributeSelection.setRanking(true);
            attributeSelection.SelectAttributes(inputDataInstances);
            Instances reduceDimensionality = attributeSelection.reduceDimensionality(inputDataInstances);

            //
            return reduceDimensionality;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
/*

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data pca
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances pca(Instances inputDataInstances)
  {
        try {

            //
            PrincipalComponents principalComponents = new PrincipalComponents();
            principalComponents.setInputFormat(inputDataInstances);
            PrincipalComponents.useFilter(inputDataInstances, principalComponents);
            principalComponents.getMaximumAttributeNames();
            principalComponents.getVarianceCovered();
            System.out.println("Maximum Attribute Names ="+principalComponents.getMaximumAttributeNames()+" Variance Covered= "+principalComponents.getVarianceCovered());
            //
            return inputDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }





 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data pca
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances pca(Instances inputDataInstances, String optionsString )
  {
        try {

            //
            PrincipalComponents principalComponents = new PrincipalComponents();
            principalComponents.setOptions(Tools.stringToArray(optionsString));
            principalComponents.setInputFormat(inputDataInstances);
            Instances resultDataInstances = PrincipalComponents.useFilter(inputDataInstances, principalComponents);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(FilteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
*/


}
