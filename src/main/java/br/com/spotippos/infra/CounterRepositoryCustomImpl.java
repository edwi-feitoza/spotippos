package br.com.spotippos.infra;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CounterRepositoryCustomImpl implements CounterRepositoryCustom {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public Integer getNextSequence(String sequenceName) {
		CountersModel countersModel = mongoOperations.findAndModify(
				query(where("_id").is(sequenceName)),
				new Update().inc("seq", 1),
				options().returnNew(true).upsert(true),
				CountersModel.class);
		return countersModel.getSeq();
	}
}
