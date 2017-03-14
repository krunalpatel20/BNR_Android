package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by krunalpa on 3/14/17.
 */

public class CrimeLab {
    private static CrimeLab crimeLab;
    private List<Crime> crimes;

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            crimes.add(crime);
        }
    }

    public static CrimeLab get(Context context) {
        if(crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }

        return crimeLab;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrimeByUUID(UUID id) {
        for(Crime crime:crimes) {
            if(crime.getId() == id) {
                return crime;
            }
        }

        return null;
    }
}
