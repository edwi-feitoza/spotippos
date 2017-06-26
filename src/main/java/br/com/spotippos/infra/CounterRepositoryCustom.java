package br.com.spotippos.infra;

public interface CounterRepositoryCustom {

	Integer getNextSequence(String sequenceName);
}
