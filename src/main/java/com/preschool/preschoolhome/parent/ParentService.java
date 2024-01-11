package com.preschool.preschoolhome.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentMapper mapper;
}
