package org.nmx.ddd.dddfromthetranches.infrastructure;

import org.nmx.ddd.dddfromthetranches.domain.model.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class MemberEntity {

	@Id
	public String id;
	public String name;
	
	
	public static MemberEntity from(Member m) {
		MemberEntity me = new MemberEntity();
		me.id = m.id();
		me.name = m.name();
		return me;
	}
	
	public Member toMember() {
		return new Member(id,name);
	}
}
