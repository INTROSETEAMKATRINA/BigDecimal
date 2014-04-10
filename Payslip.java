/*******************************************************
	 *  Class name: Payslip
 	 *  Inheritance: 
	 *  Attributes: assignment, name, periodStartDate,
     *				position, regularDaysWork, dailyRate,
     *				grossPay, late, regularPay, regularOvertime,
     *				regularOvertimePay, regularNightShiftDifferential,
     *				regularNightShiftDifferentialPay, legalHoliday,
     *				legalHolidayPay, legalHolidayOvertime, 
     *				legalHolidayOvertimePay, legalHolidayNightShiftDifferential, 
     *				legalHolidayNightShiftDifferentialPay, legalHolidayOnRestDay,
     *				legalHolidayOnRestDayPay, specialHoliday,
     *				specialHolidayPay, specialHolidayOvertime, specialHolidayOvertimePay,
     *				specialHolidayNightShiftDifferential, specialHolidayNightShiftDifferentialPay,
     *				specialHolidayOnRestDay, specialHolidayOnRestDayPay,
     *				transpoAllow, adjustments, wTax, sss,
     *				phic, hdmf, sssLoan, hdmfLoan, payrollAdvance,
     *				houseRental, uniformAndOthers, netPay, tin
	 *  Methods:	Payslip, getTIN, getAssignment, getName
     *				getPeriodStartDate, getPosition, getRegularDaysWork
     *				getDailyRate, getGrossPay, getLate, 
     *				getRegularPay, getRegularOvertime, 
     *				getRegularOvertimePay, getRegularNightShiftDifferential,
     *				getRegularNightShiftDifferentialPay, getLegalHoliday,
     *				getLegalHolidayPay, getLegalHolidayOvertime, getLegalHolidayOvertimePay,
     *				getLegalHolidayNightShiftDifferential, getLegalHolidayNightShiftDifferentialPay,
     *				getSpecialHoliday, getSpecialHolidayPay, getSpecialHolidayOvertime,
     *				getSpecialHolidayOvertimePay, getSpecialHolidayNightShiftDifferential, 
     *				getSpecialHolidayNightShiftDifferentialPay, getTranspoAllow,
     *				getAdjustments, getWTax, getSSS, getPHIC, getHDMF, 
     *				getSSSLoan, getHDMFLoan, getPayrollAdvance, 
     *				getHouseRental, getUniformAndOthers, getNetPay, 
     *				getLegalHolidayOnRestDay, getLegalHolidayOnRestDayPay,
     *				getSpecialHolidayOnRestDay, getSpecialHolidayOnRestDayPay,
     *				getTotalDeductions
	 *  Functionality: Model
	 *  Visibility: public
	 *******************************************************/

import java.util.Date;
import java.math.BigDecimal;
class Payslip{
	
	private String assignment;
	private String name;
	private Date periodStartDate;
	private String position;
	private BigDecimal regularDaysWork;
	private BigDecimal dailyRate;
	private BigDecimal grossPay;
	private BigDecimal late;
	private BigDecimal regularPay;
	private BigDecimal regularOvertime;
	private BigDecimal regularOvertimePay;
	private BigDecimal regularNightShiftDifferential;
	private BigDecimal regularNightShiftDifferentialPay;
	private BigDecimal legalHoliday;
	private BigDecimal legalHolidayPay;
	private BigDecimal legalHolidayOvertime;
	private BigDecimal legalHolidayOvertimePay;
	private BigDecimal legalHolidayNightShiftDifferential;
	private BigDecimal legalHolidayNightShiftDifferentialPay;
	private BigDecimal legalHolidayOnRestDay;
	private BigDecimal legalHolidayOnRestDayPay;
	private BigDecimal specialHoliday;
	private BigDecimal specialHolidayPay;
	private BigDecimal specialHolidayOvertime;
	private BigDecimal specialHolidayOvertimePay;
	private BigDecimal specialHolidayNightShiftDifferential;
	private BigDecimal specialHolidayNightShiftDifferentialPay;
	private BigDecimal specialHolidayOnRestDay;
	private BigDecimal specialHolidayOnRestDayPay;
	private BigDecimal transpoAllow;
	private BigDecimal adjustments;
	private BigDecimal wTax;
	private BigDecimal sss;
	private BigDecimal phic;
	private BigDecimal hdmf;
	private BigDecimal sssLoan;
	private BigDecimal hdmfLoan;
	private BigDecimal payrollAdvance;
	private BigDecimal houseRental;
	private BigDecimal uniformAndOthers;
	private BigDecimal netPay;
	private String tin;
	
	public Payslip(String tin, String assignment, String name, Date periodStartDate,
	String position, BigDecimal regularDaysWork, BigDecimal dailyRate,
	BigDecimal grossPay, BigDecimal late, BigDecimal regularPay,
	BigDecimal regularOvertime, BigDecimal regularOvertimePay,
	BigDecimal regularNightShiftDifferential,
	BigDecimal regularNightShiftDifferentialPay,
	BigDecimal legalHoliday, BigDecimal legalHolidayPay,
	BigDecimal legalHolidayOvertime, BigDecimal legalHolidayOvertimePay,
	BigDecimal legalHolidayNightShiftDifferential,
	BigDecimal legalHolidayNightShiftDifferentialPay,
	BigDecimal legalHolidayOnRestDay, BigDecimal legalHolidayOnRestDayPay,
	BigDecimal specialHoliday, BigDecimal specialHolidayPay,
	BigDecimal specialHolidayOvertime, BigDecimal specialHolidayOvertimePay,
	BigDecimal specialHolidayNightShiftDifferential,
	BigDecimal specialHolidayNightShiftDifferentialPay,
	BigDecimal specialHolidayOnRestDay, BigDecimal specialHolidayOnRestDayPay,
	BigDecimal transpoAllow, BigDecimal adjustments, BigDecimal wTax,
	BigDecimal sss, BigDecimal phic, BigDecimal hdmf, BigDecimal sssLoan,
	BigDecimal hdmfLoan, BigDecimal payrollAdvance, BigDecimal houseRental,
	BigDecimal uniformAndOthers, BigDecimal netPay){
		this.tin = tin;
		this.assignment = assignment;
		this.name = name;
		this.periodStartDate = periodStartDate;
		this.position = position;
		this.regularDaysWork = regularDaysWork.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.dailyRate = dailyRate.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.grossPay = grossPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.late = late.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.regularPay = regularPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.regularOvertime = regularOvertime.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.regularOvertimePay = regularOvertimePay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.regularNightShiftDifferential = regularNightShiftDifferential.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.regularNightShiftDifferentialPay = regularNightShiftDifferentialPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHoliday = legalHoliday.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayPay = legalHolidayPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayOvertime = legalHolidayOvertime.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayOvertimePay = legalHolidayOvertimePay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayNightShiftDifferential = legalHolidayNightShiftDifferential.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayNightShiftDifferentialPay = legalHolidayNightShiftDifferentialPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayOnRestDay = legalHolidayOnRestDay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.legalHolidayOnRestDayPay = legalHolidayOnRestDayPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHoliday = specialHoliday.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayPay = specialHolidayPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayOvertime = specialHolidayOvertime.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayOvertimePay = specialHolidayOvertimePay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayNightShiftDifferential = specialHolidayNightShiftDifferential.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayNightShiftDifferentialPay = specialHolidayNightShiftDifferentialPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayOnRestDay = specialHolidayOnRestDay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.specialHolidayOnRestDayPay = specialHolidayOnRestDayPay.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.transpoAllow = transpoAllow.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.adjustments = adjustments.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.wTax = wTax.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.sss = sss.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.phic = phic.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.hdmf = hdmf.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.sssLoan = sssLoan.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.hdmfLoan = hdmfLoan.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.payrollAdvance = payrollAdvance.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.houseRental = houseRental.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.uniformAndOthers = uniformAndOthers.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.netPay = netPay.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public String getTIN(){
		return tin;
	}
	
	public String getAssignment(){
		return assignment;
	}
	
	public String getName(){
		return name;
	}
	
	public Date getPeriodStartDate(){
		return periodStartDate;
	}
	
	public String getPosition(){
		return position;
	}
	
	public BigDecimal getRegularDaysWork(){
		return regularDaysWork;
	}
	
	public BigDecimal getDailyRate(){
		return dailyRate;
	}
	
	public BigDecimal getGrossPay(){
		return grossPay;
	}
	
	public BigDecimal getLate(){
		return late;
	}
	
	public BigDecimal getRegularPay(){
		return regularPay;
	}
	
	public BigDecimal getRegularOvertime(){
		return regularOvertime;
	}
	
	public BigDecimal getRegularOvertimePay(){
		return regularOvertimePay;
	}
	
	public BigDecimal getRegularNightShiftDifferential(){
		return regularNightShiftDifferential;
	}
	
	public BigDecimal getRegularNightShiftDifferentialPay(){
		return regularNightShiftDifferentialPay;
	}
	
	public BigDecimal getLegalHoliday(){
		return legalHoliday;
	}
	
	public BigDecimal getLegalHolidayPay(){
		return legalHolidayPay;
	}
	
	public BigDecimal getLegalHolidayOvertime(){
		return legalHolidayOvertime;
	}
	
	public BigDecimal getLegalHolidayOvertimePay(){
		return legalHolidayOvertimePay;
	}
	
	public BigDecimal getLegalHolidayNightShiftDifferential(){
		return legalHolidayNightShiftDifferential;
	}
	
	public BigDecimal getLegalHolidayNightShiftDifferentialPay(){
		return legalHolidayNightShiftDifferentialPay;
	}
	
	public BigDecimal getSpecialHoliday(){
		return specialHoliday;
	}
	
	public BigDecimal getSpecialHolidayPay(){
		return specialHolidayPay;
	}
	
	public BigDecimal getSpecialHolidayOvertime(){
		return specialHolidayOvertime;
	}
	
	public BigDecimal getSpecialHolidayOvertimePay(){
		return specialHolidayOvertimePay;
	}
	
	public BigDecimal getSpecialHolidayNightShiftDifferential(){
		return specialHolidayNightShiftDifferential;
	}
	
	public BigDecimal getSpecialHolidayNightShiftDifferentialPay(){
		return specialHolidayNightShiftDifferentialPay;
	}
	
	public BigDecimal getTranspoAllow(){
		return transpoAllow;
	}
	
	public BigDecimal getAdjustments(){
		return adjustments;
	}
	
	public BigDecimal getWTax(){
		return wTax;
	}
	
	public BigDecimal getSSS(){
		return sss;
	}
	
	public BigDecimal getPHIC(){
		return phic;
	}
	
	public BigDecimal getHDMF(){
		return hdmf;
	}
	
	public BigDecimal getSSSLoan(){
		return sssLoan;
	}
	
	public BigDecimal getHDMFLoan(){
		return hdmfLoan;
	}
	
	public BigDecimal getPayrollAdvance(){
		return payrollAdvance;
	}
	
	public BigDecimal getHouseRental(){
		return houseRental;
	}
	
	public BigDecimal getUniformAndOthers(){
		return uniformAndOthers;
	}
	
	public BigDecimal getNetPay(){
		return netPay;
	}
	
	public BigDecimal getLegalHolidayOnRestDay(){
		return legalHolidayOnRestDay;
	}
	
	public BigDecimal getLegalHolidayOnRestDayPay(){
		return legalHolidayOnRestDayPay;
	}
	
	public BigDecimal getSpecialHolidayOnRestDay(){
		return specialHolidayOnRestDay;
	}
	
	public BigDecimal getSpecialHolidayOnRestDayPay(){
		return specialHolidayOnRestDayPay;
	}
	
	public BigDecimal getTotalDeductions(){
		return sss.add(hdmf).add(wTax).add(phic).add(sssLoan).add(hdmfLoan).add(payrollAdvance).add(houseRental).add(uniformAndOthers);
	}
}