package com.connectJPA.grpc;

import com.connectJPA.dto.request.RevenueRequest;
import com.connectJPA.dto.response.MonthlyRevenueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceGrpcService extends InvoiceServiceGrpc.InvoiceServiceImplBase {

    private final InvoiceRepository invoiceRepository;


    @Override
    public void getMonthlyRevenue(RevenueRequest request, StreamObserver<MonthlyRevenueDTO> responseObserver) {
        List<Object[]> results = invoiceRepository.getMonthlyRevenue(request.getYear());
        MonthlyRevenueDTO.Builder response = MonthlyRevenueDTO.newBuilder();

        for (Object[] result : results) {
            int month = (int) result[0];
            double totalAmount = (double) result[1];

            MonthlyRevenue revenueData = MonthlyRevenue.newBuilder()
                    .setMonth(month)
                    .setTotalAmount(totalAmount)
                    .build();

            response.addRevenues(revenueData);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
