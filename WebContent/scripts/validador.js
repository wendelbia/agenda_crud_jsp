/**
 *para esse arquivo vou em criar novo folfer cliclo no folder escolho outro clico em javascript
 */
function validar(){
	
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	
	if(nome === "") {
		alert('Preencha o campo nome')
		frmContato.nome.focus()
		return false
	}else if (fone === ""){
		alert('Preencha o campo fone')
		frmContato.fone.focus()
		return false
	}else{
		//se cair nessa condição irá submeter o form
		document.forms["frmContato"].submit()
	}
}
