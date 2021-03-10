package com.eroger.service;

import java.io.FileNotFoundException;
import java.util.List;

public interface ParseService<T> {
    List<T> parse() throws FileNotFoundException;
}
