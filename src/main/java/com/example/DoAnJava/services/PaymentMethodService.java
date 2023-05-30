package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.PaymentMethod;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPayment(){
        return paymentMethodRepository.findAll();
    }

    public void savePayment(PaymentMethod paymentMethod){
        paymentMethodRepository.save(paymentMethod);
    }
    public void deletePayment(Long paymentId){
        paymentMethodRepository.deleteById(paymentId);
    }
}
