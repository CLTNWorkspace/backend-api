CREATE TRIGGER atualiza_usuario
BEFORE UPDATE ON usuario
FOR EACH ROW
EXECUTE PROCEDURE data_atualizacao();