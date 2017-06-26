package br.com.spotippos.provinces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = {"x", "y"})
public class CoordinateModel {
	
	private final Short x;
	private final Short y;
}
