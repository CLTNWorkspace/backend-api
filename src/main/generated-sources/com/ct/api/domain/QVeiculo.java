package com.ct.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVeiculo is a Querydsl query type for Veiculo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVeiculo extends EntityPathBase<Veiculo> {

    private static final long serialVersionUID = -1076513015L;

    public static final QVeiculo veiculo = new QVeiculo("veiculo");

    public final StringPath apelido = createString("apelido");

    public final DateTimePath<java.util.Date> dataAtualizacao = createDateTime("dataAtualizacao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataCriacao = createDateTime("dataCriacao", java.util.Date.class);

    public final DatePath<java.util.Date> dataExclusao = createDate("dataExclusao", java.util.Date.class);

    public final BooleanPath encontrado = createBoolean("encontrado");

    public final StringPath foto = createString("foto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath placa = createString("placa");

    public final NumberPath<Long> proprietario = createNumber("proprietario", Long.class);

    public final BooleanPath roubado = createBoolean("roubado");

    public QVeiculo(String variable) {
        super(Veiculo.class, forVariable(variable));
    }

    public QVeiculo(Path<? extends Veiculo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVeiculo(PathMetadata metadata) {
        super(Veiculo.class, metadata);
    }

}

