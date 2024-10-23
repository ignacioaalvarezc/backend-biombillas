package com.sys.bio.back.repositories.cutting;

import com.sys.bio.back.models.cutting.CutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CutBoxRepository extends JpaRepository<CutBox, Long>,
        JpaSpecificationExecutor<CutBox> {

    /**
     * FIND ALL CUT BOXES THAT FALL BETWEEN TWO SPECIFIC DATES.
     *
     * @param startDate Start date of the range.
     * @param endDate   End date of the range.
     * @return List of CutBox in the speed date range.
     */
        List<CutBox> findByDateBetween(Date startDate, Date endDate);

    /**
     * FIND ALL CUT BOXES WHOSE HANDLER NAME CONTAINS A SPECIFIC STRING, IGNORING CASE.
     *
     * @param name Partial or complete string of the name of the person.
     * @return List of Cut Boxes that match the search criteria.
     */
        List<CutBox> findByResponsibleNameContainingIgnoreCase(String name);

    /**
     * FIND ALL CUT BOXES WHOSE CUT TYPE NAME CONTAINS A SPECIFIC STRING, IGNORING CASE.
     *
     * @param name Partial or complete string of the cut type name.
     * @return List of Cut Boxes that match the search criteria.
     */
        List<CutBox> findByCutTypeNameContainingIgnoreCase(String name);

    /**
     * FIND ALL CUT BOXES ASSOCIATED WITH A SPECIFIC CUT ID.
     *
     * @param cuttingId ID of the cut.
     * @return List of the Cut Boxes associated with the cut.
     */
        List<CutBox> findByCuttingCuttingId(Long cuttingId);


    /* * AMOUNT * */

    /**
     *  OBTAINS THE TOTAL SUM OF THE AMOUNT FOR ALL TYPE CUTS.
     *
     * @return Total sum of the amount.
     */
        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Mini'")
        Integer getTotalAmountForMiniCuts();

        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Standard'")
        Integer getTotalAmountForStandardCuts();

        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Biggy'")
        Integer getTotalAmountForBiggyCuts();


    /**
     *  GETS THE TOTAL SUM OF THE AMOUNT FOR TYPE CUTS IN THE CURRENT MONTH.
     *
     * @return Total sum of the amount in the current month.
     */
        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Mini' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForMiniCutsByCurrentMonth();

        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Standard' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForStandardCutsByCurrentMonth();

        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Biggy' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForBiggyCutsByCurrentMonth();


    /**
     *  OBTAINS THE TOTAL SUM OF THE AMOUNT FOR TYPE CUTS IN THE PREVIOUS MONTH.
     *  Correctly consider the change of year.
     *
     * @return Total sum of the amount in the previous month.
     */

    /**
     * @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Mini' AND FUNCTION('YEAR_MONTH', c.date) = :previousYearMonth")
    Integer getTotalAmountForMiniCutsByPreviousMonth(@Param("previousYearMonth") YearMonth previousYearMonth);*/

        /*SE DEBE OPTIMIZAR */
        /* LA FUNCION CALCULA EL MES ANTERIOR DEL AÑO ACTUAL POR LO QUE EN ENERO CALCULARIA DICIEMBRE PERO NO DEL AÑO ANTERIOR SINO QUE DEL MISMO AÑO)*/
        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Mini' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForMiniCutsByPreviousMonth();
        /*SE DEBE OPTIMIZAR */
        /* LA FUNCION CALCULA EL MES ANTERIOR DEL AÑO ACTUAL POR LO QUE EN ENERO CALCULARIA DICIEMBRE PERO NO DEL AÑO ANTERIOR SINO QUE DEL MISMO AÑO)*/
        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Standard' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForStandardCutsByPreviousMonth();
        /*SE DEBE OPTIMIZAR */
        /* LA FUNCION CALCULA EL MES ANTERIOR DEL AÑO ACTUAL POR LO QUE EN ENERO CALCULARIA DICIEMBRE PERO NO DEL AÑO ANTERIOR SINO QUE DEL MISMO AÑO)*/
        @Query("SELECT SUM(c.amount) FROM CutBox c WHERE c.cutType.name = 'Biggy' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
        Integer getTotalAmountForBiggyCutsByPreviousMonth();


        /* * WEIGHT * */


    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Mini' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForMiniCutsByCurrentMonth();
    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Standard' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForStandardCutsByCurrentMonth();
    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Biggy' AND MONTH(c.date) = MONTH(CURRENT_DATE) AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForBiggyCutsByCurrentMonth();

    /*SE DEBE OPTIMIZAR */
    /* LA FUNCION CALCULA EL MES ANTERIOR DEL AÑO ACTUAL POR LO QUE EN ENERO CALCULARIA DICIEMBRE PERO NO DEL AÑO ANTERIOR SINO QUE DEL MISMO AÑO)*/
    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Mini' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForMiniCutsByPreviousMonth();
    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Standard' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForStandardCutsByPreviousMonth();
    @Query("SELECT SUM(c.weight) FROM CutBox c WHERE c.cutType.name = 'Biggy' AND MONTH(c.date) = MONTH(CURRENT_DATE) - 1 AND YEAR(c.date) = YEAR(CURRENT_DATE)")
    Integer getTotalWeightForBiggyCutsByPreviousMonth();



}
