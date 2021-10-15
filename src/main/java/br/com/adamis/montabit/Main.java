package br.com.adamis.montabit;

import br.com.adamis.montabit.view.Principal;

public class Main {
	

	public static void main(String[] args) {
		
		Principal principal = new Principal();
		principal.showHide(true);
		
		//zeraData(new Date());
	}
	
//	private static Date zeraData(Date data) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(data);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        System.out.println(sdf.format(calendar.getTime()));
//        return calendar.getTime();
//	}

}
