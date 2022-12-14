package com.dishIT.seatbooking.network

class RetrofitService {
    var seatSchedule = RetrofitBuilder.getInstance().create(SeatScheduleTO::class.java)
    var getfloors = RetrofitBuilder.getInstance().create(GetFloorsTO::class.java)
    var getAuthentication = RetrofitBuilder.getInstance().create(Authanticate::class.java)
    var getSeatAvailableDates = RetrofitBuilder.getInstance().create(SeatAvailableDatesTO::class.java)
    var scheduleBooking = RetrofitBuilder.getInstance().create(PostScheduleBooking::class.java)
}