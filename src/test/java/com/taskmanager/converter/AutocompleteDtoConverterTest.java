package com.taskmanager.converter;

import com.taskmanager.model.Autocomplete;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AutocompleteDtoConverterTest {

    @InjectMocks
    private AutocompleteDtoConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(Autocomplete.class);

        var actual = converter.convert(List.of(expected));

        assertEquals(expected.getId(), actual.get(0).getId());
        assertEquals(expected.getName(), actual.get(0).getName());
    }

}