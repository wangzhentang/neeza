package io.github.hotspacode.neeza.test.source;

import io.github.hotspacode.neeza.deputy.core.MockSpy;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public class PrimitiveSource {
    public float createSource() {
        MockTransport mockResponse = MockSpy.getMockData(Thread.currentThread().getStackTrace()[1]);
        if (mockResponse.isMocked()) {
            return mockResponse.getObject(mockResponse);
        }


        System.out.println("AAAAAAAAAA");
        return 1 ;
    }
}
