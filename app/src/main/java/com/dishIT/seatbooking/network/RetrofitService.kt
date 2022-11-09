package com.dishIT.seatbooking.network

class RetrofitService {
    var seatSchedule = RetrofitBuilder.getInstance().create(SeatScheduleTO::class.java)
    var getfloors = RetrofitBuilder.getInstance().create(GetFloorsTO::class.java)
    var getAuthentication = RetrofitBuilder.getInstance().create(Authanticate::class.java)
    var getSeatAvailableDates = RetrofitBuilder.getInstance().create(SeatAvailableDatesTO::class.java)
    var scheduleBooking = RetrofitBuilder.getInstance().create(PostScheduleBooking::class.java)
    var myBookings = RetrofitBuilder.getInstance().create(MyBookingsTO::class.java)
    var getAccount = RetrofitBuilder.getInstance().create(GetAccount::class.java)
    var register = RetrofitBuilder.getInstance().create(Register::class.java)
}