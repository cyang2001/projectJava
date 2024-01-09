package com.isep.eleve.javaproject.repository;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.repository.dataRepository.HistoryDataRepository;
@RunWith(MockitoJUnitRunner.class)
public class HistoryDataRepositoryTest {
    @InjectMocks
    private HistoryDataRepository historyDataRepository;
    @Test
    public void testSave() throws Exception {
        String symbol = "AAPL";
        historyDataRepository.save(symbol);

    }
}
