package com.ct.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecado is a Querydsl query type for Recado
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecado extends EntityPathBase<Recado> {

    private static final long serialVersionUID = -287971358L;

    public static final QRecado recado = new QRecado("recado");

    public final NumberPath<Long> autor = createNumber("autor", Long.class);

    public final DateTimePath<java.util.Date> dataInsercao = createDateTime("dataInsercao", java.util.Date.class);

    public final BooleanPath dislike = createBoolean("dislike");

    public final StringPath foto = createString("foto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath like = createBoolean("like");

    public final StringPath menssagem = createString("menssagem");

    public final NumberPath<Long> veiculo = createNumber("veiculo", Long.class);

    public QRecado(String variable) {
        super(Recado.class, forVariable(variable));
    }

    public QRecado(Path<? extends Recado> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecado(PathMetadata metadata) {
        super(Recado.class, metadata);
    }

}

